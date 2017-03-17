
package de.dortmund.fh.jung.myproject.create.unit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.BaseActivity;
import de.dortmund.fh.jung.myproject.God;
import de.dortmund.fh.jung.myproject.R;
import de.dortmund.fh.jung.myproject.chaosview.ChaosActivity;

public class CreateNewUnitActivity extends BaseActivity implements CreateNewUnitContract.View {

    @Inject
    CreateNewUnitContract.Presenter presenter;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView image;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_unit);

        getAppComponent().inject(this);
        presenter.bind(this);

        this.setUpOnClickListener();

    }

    private void setUpOnClickListener() {
        image = (ImageView) findViewById(R.id.create_new_unit_imageView);
        image.setOnClickListener(view -> this.takePicture());

        findViewById(R.id.button_save_new_unit).setOnClickListener(view -> {
            String name = ((EditText) findViewById(R.id.unit_name_inputfield)).getText().toString();
            presenter.setUnitName(name);
            presenter.createNewUnit();
        });

        this.setUpGodButtons();

        this.setUpOnClickListenerOnMasteryRow();
        this.setUpOnClickListenerOnGiftRow(1, R.id.greater_gifts_numbers);
        this.setUpOnClickListenerOnGiftRow(2, R.id.middle_gifts_numbers);
        this.setUpOnClickListenerOnGiftRow(3, R.id.lesser_gifts_numbers);
    }

    private void setUpGodButtons() {
        findViewById(R.id.imageView_khorne).setOnClickListener(view -> presenter.changeGod(God.KHORNE));
        findViewById(R.id.imageView_nurgle).setOnClickListener(view -> presenter.changeGod(God.NURGLE));
        findViewById(R.id.imageView_slaanesh).setOnClickListener(view -> presenter.changeGod(God.SLAANESCH));
        findViewById(R.id.imageView_tzzench).setOnClickListener(view -> presenter.changeGod(God.TZZENCH));
    }

    private void setUpOnClickListenerOnMasteryRow() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.mastery_level_numbers);
        setUpOnClickListenerById(0, 0, R.id.textView_mastery_level_zero, layout);
        setUpOnClickListenerById(0, 1, R.id.textView_mastery_level_one, layout);
        setUpOnClickListenerById(0, 2, R.id.textView_mastery_level_two, layout);
        setUpOnClickListenerById(0, 3, R.id.textView_mastery_level_three, layout);
    }

    private void setUpOnClickListenerById(final int row, final int column, final int viewId,
            final ViewGroup targetLayout) {
        TextView view = (TextView) targetLayout.findViewById(viewId);
        view.setOnClickListener(clickedView -> presenter.handleClickEvent(row, column));
    }

    private void setUpOnClickListenerOnGiftRow(final int row, final int targetLayoutId) {
        LinearLayout layout = (LinearLayout) findViewById(targetLayoutId);
        setUpOnClickListenerById(row, 0, R.id.textView_gift_number_zero, layout);
        setUpOnClickListenerById(row, 1, R.id.textView_gift_number_one, layout);
        setUpOnClickListenerById(row, 2, R.id.textView_gift_number_two, layout);
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(this, "IOError", Toast.LENGTH_SHORT).show();
                return;
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "de.dortmund.fh.jung.myproject",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyyy_HHmmss", Locale.GERMANY).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir);

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            galleryAddPic();
            presenter.saveNewPhotoLocation(currentPhotoPath);
            setImage();
        }
    }

    private void setImage() {
        int targetW = image.getWidth();
        int targetH = image.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        image.setImageBitmap(bitmap);
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbind();
    }

    @Override
    public void updateHighlights(int row, int[] highlighted) {
        LinearLayout layout;
        TextView view;
        int id;
        switch (row) {
            case 0:
                updateHighlightsForMasteryRow(highlighted);
                return;
            case 1:
                id = R.id.greater_gifts_numbers;
                break;
            case 2:
                id = R.id.middle_gifts_numbers;
                break;
            case 3:
                id = R.id.lesser_gifts_numbers;
                break;
            default:
                return;
        }
        layout = (LinearLayout) findViewById(id);

        view = (TextView) layout.findViewById(R.id.textView_gift_number_zero);
        this.setHighlightForTextView(view, highlighted[0]);
        view = (TextView) layout.findViewById(R.id.textView_gift_number_one);
        this.setHighlightForTextView(view, highlighted[1]);
        view = (TextView) layout.findViewById(R.id.textView_gift_number_two);
        this.setHighlightForTextView(view, highlighted[2]);
    }

    @Override
    public void changeThemeToKhorne() {
        this.changeKhorneIcon(true);
        this.changeNurgleIcon(false);
        this.changeSlaaneshIcon(false);
        findViewById(R.id.imageView_tzzench).setBackgroundColor(Color.TRANSPARENT);
    }

    private void changeKhorneIcon(final boolean chosen) {
        ImageView view = ((ImageView) findViewById(R.id.imageView_khorne));
        if (chosen) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.khorne_red));
            view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.khorne_white));
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.khorne));
        }
    }

    @Override
    public void changeThemeToNurgle() {
        this.changeKhorneIcon(false);
        this.changeNurgleIcon(true);
        this.changeSlaaneshIcon(false);
        findViewById(R.id.imageView_tzzench).setBackgroundColor(Color.TRANSPARENT);
    }

    private void changeNurgleIcon(final boolean chosen) {
        ImageView view = ((ImageView) findViewById(R.id.imageView_nurgle));
        if (chosen) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.nurgle_green));
            view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nurgle_complement_image));
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nurgle));
        }
    }

    @Override
    public void changeThemeToSlaanesh() {
        this.changeKhorneIcon(false);
        this.changeNurgleIcon(false);
        this.changeSlaaneshIcon(true);
        findViewById(R.id.imageView_tzzench).setBackgroundColor(Color.TRANSPARENT);
    }

    private void changeSlaaneshIcon(final boolean chosen) {
        ImageView view = ((ImageView) findViewById(R.id.imageView_slaanesh));
        if (chosen) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.daemonette_hide));
            view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.slaanesh_white));
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.slaanesh));
        }
    }

    @Override
    public void changeThemeToTzzench() {
        this.changeKhorneIcon(false);
        this.changeNurgleIcon(false);
        this.changeSlaaneshIcon(false);
        findViewById(R.id.imageView_tzzench).setBackgroundColor(ContextCompat.getColor(this, R.color.blue_horror));
    }

    @Override
    public void changeMasteryLevelVisibility(boolean show) {
        int visibleFlag;
        boolean viewsVisible = checkIfMasteryViewsVisibility();
        if (show && !viewsVisible) {
            visibleFlag = View.VISIBLE;
        } else if (!show & viewsVisible) {
            visibleFlag = View.INVISIBLE;
        } else {
            return;
        }
        findViewById(R.id.mastery_level_numbers).setVisibility(visibleFlag);
        findViewById(R.id.textView_masteryLevelLabel).setVisibility(visibleFlag);
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showKhorneMessage(String message) {
        int backgroundColor = R.color.khorne_red;
        int textColor = R.color.white;
        int image = R.drawable.khorne_white;
        this.displayMessage(message, backgroundColor, textColor, image);
    }

    @Override
    public void showSlaaneshMessage(String message) {
        int backgroundColor = R.color.daemonette_hide;
        int textColor = R.color.white;
        int image = R.drawable.slaanesh_white;
        this.displayMessage(message, backgroundColor, textColor, image);
    }

    @Override
    public void showTzeenchMessage(String message) {

    }

    @Override
    public void showNurgleMessage(String message) {
        int backgroundColor = R.color.nurgle_green;
        int textColor = R.color.nurgle_complement;
        int image = R.drawable.nurgle_complement_image;
        this.displayMessage(message, backgroundColor, textColor, image);
    }

    @Override
    public void switchToChaosActivity(final long id) {
        Intent intent = new Intent(this, ChaosActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private void displayMessage(final String message, final int backGroundColor, final int textColor, final int image) {
        Toast toast = new Toast(this);
        LinearLayout layout = new LinearLayout(this);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

        layout.setBackgroundColor(ContextCompat.getColor(this, backGroundColor));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        TextView tv = new TextView(this);
        tv.setTextColor(ContextCompat.getColor(this, textColor));
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        tv.setText(message);
        tv.setPadding(5, 5, 5, 5);
        tv.setBackgroundColor(ContextCompat.getColor(this, backGroundColor));
        tv.setLayoutParams(new LinearLayout.LayoutParams((int) (screenWidth * 0.8), (int) (screenHeight * 0.2)));

        ImageView img = new ImageView(this);
        img.setPadding(5, 15, 5, 5);
        img.setLayoutParams(new LinearLayout.LayoutParams((int) (screenWidth * 0.4), (int) (screenHeight * 0.2)));
        img.setImageResource(image);

        img.setBackgroundColor(ContextCompat.getColor(this, backGroundColor));

        layout.addView(img);
        layout.addView(tv);

        toast.setView(layout);

        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.show();
    }

    private boolean checkIfMasteryViewsVisibility() {
        return findViewById(R.id.textView_masteryLevelLabel).isShown()
                & findViewById(R.id.mastery_level_numbers).isShown();

    }

    private void updateHighlightsForMasteryRow(int[] highlighted) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.mastery_level_numbers);
        TextView view;
        view = (TextView) layout.findViewById(R.id.textView_mastery_level_zero);
        this.setHighlightForTextView(view, highlighted[0]);
        view = (TextView) layout.findViewById(R.id.textView_mastery_level_one);
        this.setHighlightForTextView(view, highlighted[1]);
        view = (TextView) layout.findViewById(R.id.textView_mastery_level_two);
        this.setHighlightForTextView(view, highlighted[2]);
        view = (TextView) layout.findViewById(R.id.textView_mastery_level_three);
        this.setHighlightForTextView(view, highlighted[3]);
    }

    private void setHighlightForTextView(TextView view, int highlight) {
        if (highlight == 1) {
            view.setTypeface(Typeface.DEFAULT_BOLD);
            view.setBackgroundColor(Color.BLACK);
            view.setTextColor(Color.WHITE);
        } else {
            view.setTypeface(Typeface.DEFAULT);
            view.setBackgroundColor(Color.WHITE);
            view.setShadowLayer(1, 2, 2, R.color.text_shadow_white);
            view.setTextColor(Color.BLACK);
        }
    }
}
