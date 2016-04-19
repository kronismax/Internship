package lituchiy.max.internship.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lituchiy.max.internship.R;
import lituchiy.max.internship.adapter.ListAdapter;
import lituchiy.max.internship.data.Appeal;
import lituchiy.max.internship.utils.Utils;

public class ListFragment extends Fragment {

    @Bind(R.id.list_view)
    ListView mListView;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view_layout, container, false);

        ButterKnife.bind(this, view);

        List<Appeal> mAppeals = Utils.getAppealsList(getContext());

        ListAdapter mAdapter = new ListAdapter(getContext());
        mAdapter.setAppeals(mAppeals);
        ViewCompat.setNestedScrollingEnabled(mListView, true);
        mListView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}