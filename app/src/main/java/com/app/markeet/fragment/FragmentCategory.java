package com.app.markeet.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.markeet.ActivityCategoryDetails;
import com.app.markeet.ActivityMain;
import com.app.markeet.R;
import com.app.markeet.adapter.AdapterCategory;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.connection.callbacks.RespCategory;
import com.app.markeet.model.Category;
import com.app.markeet.utils.NetworkCheck;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCategory extends Fragment {

    private View root_view;
    private RecyclerView recyclerView;
    private Call<RespCategory> callbackCall;
    private AdapterCategory adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.fragment_category, null);
        initComponent();
        requestListCategory();

        return root_view;
    }

    private void initComponent() {
        recyclerView = (RecyclerView) root_view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        //set data and list adapter
        adapter = new AdapterCategory(getActivity(), new ArrayList<Category>());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setVisibility(View.GONE);

        adapter.setOnItemClickListener(new AdapterCategory.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Category obj) {
                Snackbar.make(root_view, obj.name, Snackbar.LENGTH_SHORT).show();
                ActivityCategoryDetails.navigate(getActivity(), obj);
            }
        });
    }


    private void requestListCategory() {
        API api = RestAdapter.createAPI();
        callbackCall = api.getListCategory();
        callbackCall.enqueue(new Callback<RespCategory>() {
            @Override
            public void onResponse(Call<RespCategory> call, Response<RespCategory> response) {
                RespCategory resp = response.body();
                if (resp != null && resp.status.equals("success")) {
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter.setItems(resp.categories);
                    ActivityMain.getInstance().category_load = true;
                    ActivityMain.getInstance().showDataLoaded();
                } else {
                    onFailRequest();
                }
            }

            @Override
            public void onFailure(Call<RespCategory> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                if (!call.isCanceled()) onFailRequest();
            }

        });
    }

    private void onFailRequest() {
        if (NetworkCheck.isConnect(getActivity())) {
            showFailedView(R.string.msg_failed_load_data);
        } else {
            showFailedView(R.string.no_internet_text);
        }
    }

    private void showFailedView(@StringRes int message) {
        ActivityMain.getInstance().showDialogFailed(message);
    }

}
