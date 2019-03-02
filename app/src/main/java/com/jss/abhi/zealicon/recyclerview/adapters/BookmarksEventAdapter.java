package com.jss.abhi.zealicon.recyclerview.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.activities.EventDetailActivity;
import com.jss.abhi.zealicon.model.EventData;
import com.jss.abhi.zealicon.model.InnerData;

import java.util.ArrayList;

public class BookmarksEventAdapter extends RecyclerView.Adapter<BookmarksEventAdapter.BookmarkEventScheduleViewHolder> {

    public Context context;
    private ArrayList<EventData> bookmarkEventScheduleArrayList;

    public class BookmarkEventScheduleViewHolder extends RecyclerView.ViewHolder{
        private TextView bookmark_event_name;
        private TextView event_time;
        private TextView event_location;
        private TextView event_category;
        private View bookmark_event_schedule_layout;


        public BookmarkEventScheduleViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            bookmark_event_name = itemView.findViewById(R.id.titleTextView);
           // event_time = itemView.findViewById(R.id.timeTextView);
            //event_location= itemView.findViewById(R.id.locationTextView);
            bookmark_event_schedule_layout = itemView.findViewById(R.id.bookmark_event_schedule_layout);

            // event_category = itemView.findViewById(R.id.categoryTextView);
        }
    }

    public BookmarksEventAdapter(ArrayList<EventData> bookmarkEventScheduleArrayList) {
        this.bookmarkEventScheduleArrayList = bookmarkEventScheduleArrayList;
    }

    @Override

    public BookmarkEventScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_event_layout,parent,false);
        return new BookmarkEventScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookmarkEventScheduleViewHolder holder, int position) {
        final EventData eventInnerData = bookmarkEventScheduleArrayList.get(position);
        holder.bookmark_event_name.setText(eventInnerData.getName());
        //holder.event_time.setText(Integer.toString(eventInnerData.getEvent_time()));
   /* holder.event_location.setText((eventInnerData.getEvent_location()));
    holder.event_category.setText(eventInnerData.getCategory());*/

        holder.bookmark_event_schedule_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("eventData", eventInnerData);
                context.startActivity(intent);
            }
        });
    }

    @Override public int getItemCount() {
        return bookmarkEventScheduleArrayList.size();
    }

    public void setData(ArrayList<EventData> list) {
        bookmarkEventScheduleArrayList.clear();
        bookmarkEventScheduleArrayList.addAll(list);
        notifyDataSetChanged();
    }

}
