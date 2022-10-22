package khaf.d4me.charly_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCategorias extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolder> {
    private ArrayList<Categorias> mData;
    private LayoutInflater mInflater;
    private Context context;

    public AdaptadorCategorias(ArrayList<Categorias> itemlist, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemlist;
    }

    @Override
    public AdaptadorCategorias.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_categorias, null);
        return new AdaptadorCategorias.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorCategorias.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItem(ArrayList<Categorias> items) {
        mData = items;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre, Estatus;
        Button btnEditarCat, btnBorrarCar;
        ViewHolder(View itemView) {
            super(itemView);
            //  iconImage=itemView.findViewById(R.id.iconlist);
            Nombre = itemView.findViewById(R.id.lblNombreCat);
            Estatus = itemView.findViewById(R.id.lblEstatusCat);
            btnEditarCat = itemView.findViewById(R.id.btnEditar);
            btnBorrarCar = itemView.findViewById(R.id.btnEliminar);
        }

        void bindData(final Categorias item) {
            //iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            Nombre.setText(item.getCategoria());
            String est = "INACTIVO";
            if(item.getEstatus()==1)
                est = "ACTIVO";
            Estatus.setText(est);
        }
    }
}