package com.yasinhajilou.dileit.view.fragment.leitnerreview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasinhajilou.dileit.databinding.FragmentReviewReportBinding;
import com.yasinhajilou.dileit.viewmodel.ReviewLeitnerSharedViewModel;

public class ReviewReportFragment extends Fragment {

    private FragmentReviewReportBinding mBinding;
    private ReviewLeitnerSharedViewModel mSharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = new ViewModelProvider(requireActivity()).get(ReviewLeitnerSharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentReviewReportBinding.inflate(inflater, container, false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSharedViewModel.getReviewedCards().observe(getViewLifecycleOwner(), integer -> {
            if (integer > 0) {
                mBinding.tvNoDataReviewing.setVisibility(View.GONE);
                mBinding.animTrophy.setVisibility(View.VISIBLE);
                mBinding.tvLabelReviewedCards.setVisibility(View.VISIBLE);
                mBinding.tvReviewedCards.setVisibility(View.VISIBLE);
                mBinding.tvReviewedCards.setText(String.valueOf(integer));
            }
        });

        mSharedViewModel.getReviewAgainCards().observe(getViewLifecycleOwner(), integer -> {
            // integer > 0 user should review again some cards
            if (integer > 0) {
                mBinding.tvReviewAgainCards.setVisibility(View.VISIBLE);
                mBinding.tvLabelReviewAgainCards.setVisibility(View.VISIBLE);
                mBinding.tvReviewAgainCards.setText(String.valueOf(integer));
                mBinding.animTrophy.setVisibility(View.GONE);
                mBinding.animClap.setVisibility(View.VISIBLE);
                mBinding.animClap.playAnimation();
            }
        });

        mSharedViewModel.getSyncedCards().observe(getViewLifecycleOwner() , ints -> {
            int allReviewed = ints[0];
            int reviewAgain = ints[1];

            if (reviewAgain > 0){
                mBinding.animClap.setVisibility(View.VISIBLE);
                mBinding.animTrophy.setVisibility(View.GONE);
            }else {
                mBinding.animClap.setVisibility(View.GONE);
                mBinding.animTrophy.setVisibility(View.VISIBLE);
            }
        });

        mBinding.fabGoHome.setOnClickListener(view1 -> getActivity().finish());
    }
}
