package fr.up.projetandroid.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.up.projetandroid.R;
import fr.up.projetandroid.entities.Session;
import io.reactivex.rxjava3.annotations.NonNull;

public class SessionsRecyclerViewAdapter extends RecyclerView.Adapter<SessionsRecyclerViewAdapter.SessionViewHolder>{
    private List<Session> sessions;

    public SessionsRecyclerViewAdapter(List<Session> sessions){
        this.sessions = sessions;
    }

    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_session_row_item_view, parent, false);
        return new SessionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position) {
        Session s = sessions.get(position);
        holder.updateData(s);
    }
    @Override
    public int getItemCount() {
        return sessions.size();
    }

    public static class SessionViewHolder extends RecyclerView.ViewHolder{
        private TextView pseudo;
        private TextView date;
        private ImageButton button;
        public SessionViewHolder(@NonNull View itemView) {
            super(itemView);
            pseudo = itemView.findViewById(R.id.rowItemTextViewPseudo);
            date = itemView.findViewById(R.id.rowItemTextViewDate);
            button = itemView.findViewById(R.id.rowItemButton);
        }
        public void updateData(Session s) {
            pseudo.setText(s.getPseudo());
            date.setText(s.getDate());
        }
    }
}
