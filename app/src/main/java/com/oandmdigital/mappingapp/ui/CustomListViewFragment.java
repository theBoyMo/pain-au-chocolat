package com.oandmdigital.mappingapp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.event.LocationClickEvent;
import com.oandmdigital.mappingapp.model.Shop;
import com.oandmdigital.mappingapp.model.ShopData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

public class CustomListViewFragment extends Fragment {


    public static CustomListViewFragment newInstance() {
        return new CustomListViewFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView view = (ListView) inflater.inflate(R.layout.list_view, container, false);

        // populate the array adapter with the data set & bind it to the listview
        List<Shop> items = new ArrayList<>(Arrays.asList(ShopData.list));
        LocationArrayAdapter adapter = new LocationArrayAdapter(items);
        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // post the selected item to the bus, allowing the activity to deal with click event
                EventBus.getDefault()
                        .post(new LocationClickEvent((Shop) parent.getItemAtPosition(position),
                                                LocationClickEvent.LIST_ITEM_CLICK_EVENT));
            }
        });

        return view;
    }


    private class LocationArrayAdapter extends ArrayAdapter<Shop> {

        public LocationArrayAdapter(List<Shop> items) {
            super(getActivity(), 0, items);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if(holder == null) {
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            // populate the holder elements
            Shop item = getItem(position);
            holder.locationName.setText(item.getName());
            holder.locationDistance.setText(String.format("%s miles", String.valueOf(item.getDistance())));
            holder.locationStreet.setText(item.getAddress().getStreet());
            holder.locationRating.setText(String.format("Rating: %.1f", item.getRating()));

            // set the imageview
            holder.locationThumbnail.setImageResource(ShopData.getImageDrawable(position));

            return convertView;
        }
    }


    private class ViewHolder {
        ImageView locationThumbnail;
        TextView locationName;
        TextView locationDistance;
        TextView locationStreet;
        TextView locationRating;

        public ViewHolder(View row) {
            locationThumbnail = (ImageView) row.findViewById(R.id.shop_image);
            locationName = (TextView) row.findViewById(R.id.location_name);
            locationDistance = (TextView) row.findViewById(R.id.location_distance);
            locationStreet = (TextView) row.findViewById(R.id.location_street);
            locationRating = (TextView) row.findViewById(R.id.location_rating);
        }
    }



}
