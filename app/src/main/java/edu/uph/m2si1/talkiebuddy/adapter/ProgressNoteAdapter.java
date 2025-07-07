package edu.uph.m2si1.talkiebuddy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.uph.m2si1.talkiebuddy.R;
import edu.uph.m2si1.talkiebuddy.model.ProgressNote;

public class ProgressNoteAdapter extends RecyclerView.Adapter<ProgressNoteAdapter.ViewHolder> {

    private List<ProgressNote> progressNoteList;

    // Constructor
    public ProgressNoteAdapter(List<ProgressNote> progressNoteList) {
        this.progressNoteList = progressNoteList;
    }

    // ViewHolder inner class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvDate, txvTopic, txvWords, txvSummary, txvConfidence, txvDuration, txvNotes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvDate = itemView.findViewById(R.id.txvDate);
            txvTopic = itemView.findViewById(R.id.txvTopic);
            txvWords = itemView.findViewById(R.id.txvWords);
            txvSummary = itemView.findViewById(R.id.txvSummary);
            txvConfidence = itemView.findViewById(R.id.txvConfidence);
            txvDuration = itemView.findViewById(R.id.txvDuration);
            txvNotes = itemView.findViewById(R.id.txvNotes);
        }
    }

    @NonNull
    @Override
    public ProgressNoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_progress_note.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_progress_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgressNoteAdapter.ViewHolder holder, int position) {
        ProgressNote note = progressNoteList.get(position);

        holder.txvDate.setText("Date: " + note.getDate());
        holder.txvTopic.setText("Topic: " + note.getTopic());
        holder.txvWords.setText("Words Learned: " + note.getWords());
        holder.txvSummary.setText("Summary: " + note.getSummary());
        holder.txvConfidence.setText("Confidence: " + note.getConfidence());
        holder.txvDuration.setText("Duration: " + note.getDuration());
        holder.txvNotes.setText("Notes: " + note.getNotes());
    }

    @Override
    public int getItemCount() {
        return progressNoteList.size();
    }
}
