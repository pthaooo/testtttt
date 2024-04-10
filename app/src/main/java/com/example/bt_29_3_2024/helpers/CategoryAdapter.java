package com.example.bt_29_3_2024.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bt_29_3_2024.R;
import com.example.bt_29_3_2024.activities.SubCategoryActivity;
import com.example.bt_29_3_2024.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private List<Category> categoryList;
    private Context context;
//    private OnClickListener onClickListener;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            MyViewHolder holder,
            @SuppressLint("RecyclerView") int position
    ) {

        Category ans = categoryList.get(position);
        holder.categoryName.setText(ans.getName());
        Glide.with(context)
             .load(ans.getImages())
             .into(holder.categoryImage);

        holder.categoryImage.setOnClickListener(
                v -> {
                    RedirectHelper.redirect(
                            context,
                            SubCategoryActivity.class,
                            "idcategory",
                            ans.getId(),
                            "namecategory",
                            ans.getName()
                    );
                }
        );
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final ImageView categoryImage;
        private final TextView categoryName;

        public MyViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.image_cate);
            categoryName = itemView.findViewById(R.id.tvNameCategory);
        }
    }
}
