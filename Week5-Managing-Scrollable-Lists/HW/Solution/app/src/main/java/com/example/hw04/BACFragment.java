package com.example.hw04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hw04.databinding.FragmentBacBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BACFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BACFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BACFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BACFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BACFragment newInstance(String param1, String param2) {
        BACFragment fragment = new BACFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Profile mProfile;
    public void setProfile(Profile profile){
        mProfile = profile;
    }

    FragmentBacBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBacBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ArrayList<Drink> mDrinks;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("BAC Calculator");

        binding.buttonSetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSetProfile();
            }
        });

        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clearAllDrinks();
                mProfile = null;
                binding.buttonViewDrinks.setEnabled(false);
                binding.buttonAddDrink.setEnabled(false);
                binding.textViewWeightGender.setText("N/A");
                //display the BAC ..
                binding.textViewStatus.setText("You're safe");
                binding.viewStatus.setBackgroundResource(R.color.safe_color);
                binding.textViewBACLevel.setText("BAC Level: 0.000");
                binding.textViewNoDrinks.setText("# Drinks: 0");
            }
        });

        binding.buttonAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddDrink();
            }
        });

        binding.buttonViewDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrinks.size() > 0){
                    mListener.gotoViewDrinks();
                } else {
                    Toast.makeText(getActivity(), "No drinks to view !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mDrinks = mListener.getAllDrinks();

        if(mProfile == null){
            binding.buttonViewDrinks.setEnabled(false);
            binding.buttonAddDrink.setEnabled(false);
            binding.textViewWeightGender.setText("N/A");
            binding.textViewStatus.setText("You're safe");
            binding.viewStatus.setBackgroundResource(R.color.safe_color);
            binding.textViewBACLevel.setText("BAC Level: 0.000");
            binding.textViewNoDrinks.setText("# Drinks: 0");
        } else {
            binding.buttonViewDrinks.setEnabled(true);
            binding.buttonAddDrink.setEnabled(true);
            binding.textViewWeightGender.setText(mProfile.getWeight() + " (" + mProfile.getGender() + ")");
            displayBAC();
        }
    }

    private void displayBAC(){
        binding.textViewNoDrinks.setText("# Drinks: " + mDrinks.size());
        double value_r = 0.66;
        if(mProfile.getGender().equals("Male")){
            value_r = 0.73;
        }
        double value_a = 0.0;

        for (Drink drink: mDrinks) {
            value_a = value_a + drink.getAlcohol() * drink.getSize()/100.00;
        }

        double bac = value_a * 5.14 /(mProfile.getWeight() * value_r);
        binding.textViewBACLevel.setText("BAC Level: " + String.format("%.3f", bac));

        if(bac <= 0.08){
            binding.textViewStatus.setText("You're safe");
            binding.viewStatus.setBackgroundResource(R.color.safe_color);
        } else if(bac <= 0.2){
            binding.textViewStatus.setText("Be Careful");
            binding.viewStatus.setBackgroundResource(R.color.becareful_color);
        } else {
            binding.textViewStatus.setText("Over the limit!");
            binding.viewStatus.setBackgroundResource(R.color.overlimit_color);
        }
    }

    BACFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (BACFragmentListener) context;
    }

    interface BACFragmentListener{
        void gotoSetProfile();
        void clearAllDrinks();
        ArrayList<Drink> getAllDrinks();
        void gotoAddDrink();

        void gotoViewDrinks();
    }
}