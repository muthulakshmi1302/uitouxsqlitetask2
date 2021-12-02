package com.app.sqlite_project.Features.StudentCRUD.ShowStudentList;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sqlite_project.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    TextView mark1TextView;
    TextView mark2TextView;
    TextView mark3TextView;
    TextView rankTextView;
    TextView resultTextView;
    ImageView crossButtonImageView;
    ImageView editButtonImageView;

    public CustomViewHolder(View itemView) {
        super(itemView);

        nameTextView = itemView.findViewById(R.id.nameTextView);
        mark1TextView = itemView.findViewById(R.id.mark11TextView);
        mark2TextView = itemView.findViewById(R.id.mark22TextView);
        mark3TextView = itemView.findViewById(R.id.mark33TextView);
        rankTextView = itemView.findViewById(R.id.rank1TextView);
        resultTextView = itemView.findViewById(R.id.result1TextView);
        crossButtonImageView = itemView.findViewById(R.id.crossImageView);
        editButtonImageView = itemView.findViewById(R.id.editImageView);
    }
}
