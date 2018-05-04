package com.example.ahmedetman.peopleapitask.views.adapter;

import android.content.pm.ApplicationInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Filter;
import android.widget.Filterable;

import com.example.ahmedetman.peopleapitask.R;
import com.example.ahmedetman.peopleapitask.models.CharacterItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

public class CharactersAdapter extends
        RecyclerView.Adapter<CharactersAdapter.CustomViewHolder> implements Filterable {

    private CharacterClickListener mFavCharacterClickListener;
    private CharacterClickListener mCharacterClickListener;

    public void setOnFavClickListener(CharacterClickListener favClickListener) {
        this.mFavCharacterClickListener = favClickListener;
    }

    public void setOnItemClickListener(CharacterClickListener characterClickListener) {
        this.mCharacterClickListener = characterClickListener;
    }


    private List<CharacterItem> mCharactersList;
    private List<CharacterItem> characterListFiltered;

    public CharactersAdapter(List<CharacterItem> charactersList) {
        this.characterListFiltered = charactersList;
        this.mCharactersList = charactersList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.bindItem(characterListFiltered.get(position));
        holder.img_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mFavCharacterClickListener != null){
                    mFavCharacterClickListener.onCharacterItemClick(characterListFiltered.get(position));
                }
            }
        });

        holder.layout_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCharacterClickListener != null){
                    mCharacterClickListener.onCharacterItemClick(characterListFiltered.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return characterListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    characterListFiltered = mCharactersList;
                } else {
                    List<CharacterItem> filteredList = new ArrayList<>();
                    for (CharacterItem row : mCharactersList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())
                                || row.getName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    characterListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = characterListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                characterListFiltered = (ArrayList<CharacterItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_year;
        View v_separator;
        ImageView img_favorite;
        LinearLayout layout_container;

        public CustomViewHolder(View view) {
            super(view);
            this.tv_name = view.findViewById(R.id.tv_name);
            this.tv_year = view.findViewById(R.id.tv_year);
            this.v_separator = view.findViewById(R.id.item_separator);
            this.img_favorite = view.findViewById(R.id.img_fav);
            this.layout_container = view.findViewById(R.id.layout_text_container);
        }

        void bindItem(CharacterItem characterItem) {
            tv_name.setText(characterItem.getName());
            tv_year.setText(characterItem.getBirth_year());
        }
    }

    public interface CharacterClickListener{
        void onCharacterItemClick(CharacterItem characterItem);
    }

}
