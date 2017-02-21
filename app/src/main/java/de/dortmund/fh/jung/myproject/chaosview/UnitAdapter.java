
package de.dortmund.fh.jung.myproject.chaosview;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import de.dortmund.fh.jung.myproject.R;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ViewHolder> {

    private List<Unit> units;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

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
        units = Collections.emptyList();
    }

    public UnitAdapter(List<Unit> units) {
        this.units = units;
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
        holder.overflow.setOnClickListener((view) -> showPopupMenu(holder.overflow));
    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.unit_context_menu, popup.getMenu());
        popup.setOnMenuItemClickListener((item) -> {
            switch (item.getItemId()) {
                case R.id.remove_unit:
                    Toast.makeText(view.getContext(), "TODO DELETE", Toast.LENGTH_SHORT).show();
                    return true;
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
