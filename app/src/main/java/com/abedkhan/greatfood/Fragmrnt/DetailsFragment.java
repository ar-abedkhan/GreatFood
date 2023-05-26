package com.abedkhan.greatfood.Fragmrnt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abedkhan.greatfood.R;
import com.abedkhan.greatfood.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment {

    public DetailsFragment() {
    }
FragmentDetailsBinding binding;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDetailsBinding.inflate(getLayoutInflater(),container,false);


























        return binding.getRoot();
    }
}