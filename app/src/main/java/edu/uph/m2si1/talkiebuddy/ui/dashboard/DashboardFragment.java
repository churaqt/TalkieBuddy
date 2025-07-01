package edu.uph.m2si1.talkiebuddy.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.uph.m2si1.talkiebuddy.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate layout dengan View Binding
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot(); // langsung return root layout
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
