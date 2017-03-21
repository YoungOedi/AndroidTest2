
package de.dortmund.fh.jung.myproject.chaosview;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import de.dortmund.fh.jung.myproject.R;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ViewHolder> {

    private List<Unit> units;
    private PublishSubject<Unit> publishRemoval = PublishSubject.create();
    private PublishSubject<Unit> publishEdit = PublishSubject.create();

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView unitName;
        public ImageView picture, overflow;

        public ViewHolder(View v) {
            super(v);
            unitName = (TextView) v.findViewById(R.id.unitName);
            picture = (ImageView) v.findViewById(R.id.picture);
            overflow = (ImageView) v.findViewById(R.id.overflow);
        }
    }

    public UnitAdapter() {
        this(Collections.emptyList());
    }

    public UnitAdapter(List<Unit> units) {
        this.units = units;
    }

    public Observable<Unit> provideRemovalObservable(){
        return publishRemoval;
    }

    public Observable<Unit> provideEditObservable(){
        return publishEdit;
    }

    public void setData(List<Unit> units) {
        this.units = units;
    }

    @Override
    public UnitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View unitView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_unit_layout, parent, false);
        return new ViewHolder(unitView);
    }

    @Override
    public void onBindViewHolder(UnitAdapter.ViewHolder holder, int position) {
        Unit unit = units.get(position);
        holder.unitName.setText(unit.getName());
        holder.overflow.setOnClickListener((view) -> {
           handleClickOnOverflow(holder.overflow, position, unit);
        });
    }

    private void handleClickOnOverflow(final View view, final int position, final Unit unit) {
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.unit_context_menu, popup.getMenu());
        popup.setOnMenuItemClickListener((item) -> {
            switch (item.getItemId()) {
                case R.id.remove_unit:
                    publishRemoval.onNext(unit);
                    units.remove(unit);
                    notifyItemRemoved(position);
                    Toast.makeText(view.getContext(), "Unit Deleted", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.edit_unit:
                    publishEdit.onNext(unit);
                    //TODO Edit
                    notifyDataSetChanged();
                default:
            }
            return false;
        });
        popup.show();
    }

    @Override
    public int getItemCount() {
        return units.size();
    }
}
