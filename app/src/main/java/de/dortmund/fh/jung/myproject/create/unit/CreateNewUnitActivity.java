
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
        ((ImageView) findViewById(R.id.imageView_nurgle)).setBackgroundColor(Color.TRANSPARENT);
        this.changeSlaaneshIcon(false);
        ((ImageView) findViewById(R.id.imageView_tzzench)).setBackgroundColor(Color.TRANSPARENT);
    }

    private void changeKhorneIcon(final boolean chosen){
        ImageView view = ((ImageView) findViewById(R.id.imageView_khorne));
        if(chosen) {
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
        ((ImageView) findViewById(R.id.imageView_nurgle))
                .setBackgroundColor(ContextCompat.getColor(this, R.color.nurgle_green));
        this.changeSlaaneshIcon(false);
        ((ImageView) findViewById(R.id.imageView_tzzench)).setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public void changeThemeToSlaanesh() {
        this.changeKhorneIcon(false);
        ((ImageView) findViewById(R.id.imageView_nurgle)).setBackgroundColor(Color.TRANSPARENT);
        this.changeSlaaneshIcon(true);
        ((ImageView) findViewById(R.id.imageView_tzzench)).setBackgroundColor(Color.TRANSPARENT);
    }

    private void changeSlaaneshIcon(final boolean chosen){
        ImageView view = ((ImageView) findViewById(R.id.imageView_slaanesh));
        if(chosen) {
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
        ((ImageView) findViewById(R.id.imageView_nurgle)).setBackgroundColor(Color.TRANSPARENT);
        this.changeSlaaneshIcon(false);
        ((ImageView) findViewById(R.id.imageView_tzzench)).setBackgroundColor(Color.TRANSPARENT);
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
            view.setTextSize(23);
            view.setTypeface(Typeface.DEFAULT_BOLD);
            view.setBackgroundColor(Color.BLACK);
            view.setTextColor(Color.WHITE);
        } else {
            view.setTextSize(20);
            view.setTypeface(Typeface.DEFAULT);
            view.setBackgroundColor(Color.WHITE);
            view.setShadowLayer(1, 2, 2, R.color.text_shadow_white);
            view.setTextColor(Color.BLACK);
        }
    }
}
