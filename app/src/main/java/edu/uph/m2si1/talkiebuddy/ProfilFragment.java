package edu.uph.m2si1.talkiebuddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.uph.m2si1.talkiebuddy.adapter.ProgressNoteAdapter;
import edu.uph.m2si1.talkiebuddy.model.ProgressNote;
import io.realm.Realm;
import io.realm.RealmResults;

public class ProfilFragment extends Fragment {

    private RecyclerView rvProgress;
    private Button btnAddProgress;
    private ProgressNoteAdapter adapter;
    private List<ProgressNote> noteList = new ArrayList<>();
    private Realm realm;

    public ProfilFragment() {
        // Required empty public constructor
    }

    public static ProfilFragment newInstance(String param1, String param2) {
        ProfilFragment fragment = new ProfilFragment();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
        args.putString("ARG_PARAM2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(requireContext()); // init Realm
        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvProgress = view.findViewById(R.id.rvProgress);
        btnAddProgress = view.findViewById(R.id.btnAddProgress);

        rvProgress.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadNotes(); // load from Realm

        btnAddProgress.setOnClickListener(v -> {
            AddNoteDialog dialog = new AddNoteDialog();
            dialog.setNoteSavedListener(this::loadNotes); // callback ke loadNotes()
            dialog.show(getParentFragmentManager(), "AddNoteDialog");
        });

    }

    private void loadNotes() {
        RealmResults<ProgressNote> results = realm.where(ProgressNote.class).findAll();
        noteList.clear();
        noteList.addAll(results);
        adapter = new ProgressNoteAdapter(noteList);
        rvProgress.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}
