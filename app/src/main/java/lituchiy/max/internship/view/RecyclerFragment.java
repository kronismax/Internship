package lituchiy.max.internship.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lituchiy.max.internship.R;
import lituchiy.max.internship.adapter.RecyclerAdapter;
import lituchiy.max.internship.data.Appeal;
import lituchiy.max.internship.utils.Utils;

public class RecyclerFragment extends Fragment {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);

        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        List<Appeal> mAppeals = Utils.getAppealsList(getContext());

        RecyclerAdapter mRecyclerAdapter = new RecyclerAdapter(getContext());
        mRecyclerAdapter.setAppeals(mAppeals);

        mRecyclerView.setAdapter(mRecyclerAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}