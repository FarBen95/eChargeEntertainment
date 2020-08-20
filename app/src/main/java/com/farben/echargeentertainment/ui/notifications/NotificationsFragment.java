package com.farben.echargeentertainment.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farben.echargeentertainment.R;
import com.farben.echargeentertainment.db.entity.WebsiteEntity;
import com.farben.echargeentertainment.ui.WebsiteListAdapter;
import com.farben.echargeentertainment.viewmodel.MainViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationsFragment extends Fragment {

    private MainViewModel mainViewModel;
    private WebsiteListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        mainViewModel.getWebsiteList().observe(getViewLifecycleOwner(), new Observer<List<WebsiteEntity>>() {
            @Override
            public void onChanged(@Nullable final List<WebsiteEntity> websites) {
                // Update the cached copy of the websites in the adapter.
                if (adapter != null) {
                    adapter.setWebsites(websites);
                }
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.notification_recyclerview);
        adapter = new WebsiteListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }
}