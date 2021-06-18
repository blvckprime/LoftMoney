package com.example.loftmoney.screens.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.loftmoney.R;
import com.example.loftmoney.screens.dashboard.adapter.FragmentAdapter;
import com.example.loftmoney.screens.dashboard.adapter.FragmentItem;
import com.example.loftmoney.screens.money.MoneyEditListener;
import com.example.loftmoney.screens.money.MoneyFragment;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class DashboardFragment extends Fragment implements EditModeListener {

    private Toolbar toolbarView;
    private ImageView backButtonView;
    private ImageView dashboardActionView;
    private TabLayout tabContainerView;
    private TextView dashboardTitleView;
    private ViewPager containerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return layoutInflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<FragmentItem> fragments = new ArrayList<>();
        fragments.add(new FragmentItem(new MoneyFragment(), getString(R.string.title_expense)));
        fragments.add(new FragmentItem(new MoneyFragment(), getString(R.string.title_incomes)));
        fragments.add(new FragmentItem(new MoneyFragment(), getString(R.string.title_balance)));

        toolbarView = view.findViewById(R.id.toolbarView);
        backButtonView = view.findViewById(R.id.backButtonView);
        dashboardActionView = view.findViewById(R.id.dashboardActionView);
        dashboardTitleView = view.findViewById(R.id.dashboardTitleView);

        backButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEditStatus();
            }
        });

        dashboardActionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle(getString(R.string.delete_items_title))
                        .setMessage(getString(R.string.delete_items_message))
                        .setNegativeButton(R.string.action_no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton(R.string.action_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                clearSelectedItems();
                            }
                        })
                        .show();
            }
        });

        containerView = view.findViewById(R.id.containerView);
        containerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                clearEditStatus();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabContainerView = view.findViewById(R.id.tabContainerView);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragments, getChildFragmentManager(), 0);
            containerView.setAdapter(fragmentAdapter);
            containerView.setOffscreenPageLimit(3);
            tabContainerView.setupWithViewPager(containerView);
        }


        @Override
        public void onEditModeChanged ( boolean status){
         toolbarView.setBackgroundColor(ContextCompat.getColor(getContext(),
         status ? R.color.selectionColorPrimary : R.color.blue));
         dashboardActionView.setVisibility(status ? View.VISIBLE : View.GONE);
         backButtonView.setVisibility(status ? View.VISIBLE : View.GONE);
         tabContainerView.setBackgroundColor(ContextCompat.getColor(getContext(),
                 status ? R.color.selectionColorPrimary : R.color.blue));
        }

        @Override
        public void onCounterChanged ( int newCount){
            if(newCount >= 0) {
                dashboardTitleView.setText(getString(R.string.selected) + " " + newCount);
            } else {
                dashboardTitleView.setText(getString(R.string.title_dashboard));
            }
        }

        private void clearEditStatus() {
            Fragment fragment = getChildFragmentManager().getFragments().get(containerView.getCurrentItem());
            if (fragment instanceof MoneyEditListener) {
                ((MoneyEditListener) fragment).onClearEdit();
            }
        }

        private void clearSelectedItems() {
            Fragment fragment = getChildFragmentManager().getFragments().get(containerView.getCurrentItem());
            if (fragment instanceof MoneyEditListener){
                ((MoneyEditListener) fragment).onCLearSelecredClick();
            }
        }
    }
