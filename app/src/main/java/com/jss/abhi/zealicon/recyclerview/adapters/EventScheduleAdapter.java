package com.jss.abhi.zealicon.recyclerview.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.activities.EventDetailActivity;
import com.jss.abhi.zealicon.model.EventData;
import com.jss.abhi.zealicon.utils.NotifierUtil;

import java.util.ArrayList;

import static com.jss.abhi.zealicon.utils.Society.getSociety;
import static com.jss.abhi.zealicon.utils.TitleCaseConverter.toTitleCase;

/**
 * Created by abhi on 15/2/18.
 */

public class EventScheduleAdapter extends RecyclerView.Adapter<EventScheduleAdapter.EventViewHolder> {

    public Context context;
    private ArrayList<? extends EventData> eventScheduleArrayList;

    public class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView event_name;
        private TextView event_time;
        private TextView event_location;
        private TextView event_society;
        private View event_schedule_layout;
        private ImageView bell_notify;


        public EventViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            event_name = itemView.findViewById(R.id.titleTextView);
            event_time = itemView.findViewById(R.id.timeTextView);
            event_location = itemView.findViewById(R.id.locationTextView);
            event_schedule_layout = itemView.findViewById(R.id.event_schedule_layout);
            event_society = itemView.findViewById(R.id.societyTextView);
            bell_notify = itemView.findViewById(R.id.notify_bell_icon);
        }
    }

    public EventScheduleAdapter(ArrayList<? extends EventData> eventScheduleArrayList) {
        this.eventScheduleArrayList = eventScheduleArrayList;
    }

    @Override

    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventScheduleAdapter.EventViewHolder holder, final int position) {
        final EventData eventInnerData = eventScheduleArrayList.get(position);
        final int notifyId = context.getSharedPreferences("notify", 0).getInt(eventInnerData.getName(), 0);
        if (notifyId == 0) {
            holder.bell_notify.setColorFilter(ContextCompat.getColor(context, R.color.app_white), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            holder.bell_notify.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        holder.event_name.setText(toTitleCase(eventInnerData.getName()));
        holder.event_society.setText(getSociety(Integer.valueOf(eventInnerData.getSocietyId())));

        //holder.event_time.setText(Integer.toString(eventInnerData.getEvent_time()));
   /* holder.event_location.setText((eventInnerData.getEvent_location()));
    holder.event_category.setText(eventInnerData.getCategory());*/

        holder.bell_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notifyId == 0) {
                    //holder.bell_notify.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
                    NotifierUtil.notifyme(context, "01-03-2019 22:00", eventInnerData.getName());
                    Toast.makeText(context, "You will be notified for this event", Toast.LENGTH_LONG).show();
                } else {
                    //holder.bell_notify.setColorFilter(ContextCompat.getColor(context, R.color.app_white), android.graphics.PorterDuff.Mode.SRC_IN);
                    NotifierUtil.canclenotifyme(context, eventInnerData.getName());
                    Toast.makeText(context, "You will not be notified for this event", Toast.LENGTH_LONG).show();
                }
                notifyItemChanged(position);

            }
        });
        holder.event_schedule_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("eventData", eventInnerData);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return eventScheduleArrayList.size();
    }


}
