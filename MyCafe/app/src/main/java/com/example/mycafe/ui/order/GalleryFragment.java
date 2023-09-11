package com.example.mycafe.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycafe.R;
import com.example.mycafe.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        GalleryViewModel galleryViewModel =
//                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textGallery;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.menuSpinner.setOnItemSelectedListener(menuSpinner);

        Toast.makeText(getContext(), "Order ！", Toast.LENGTH_LONG).show();
        return root;
    }

    /*public void btn_onClick(View view){
        switch (view.getId()) {
            case R.id.order_check:{
                //Toast.makeText(getApplicationContext(), "order_check onClick！", Toast.LENGTH_LONG).show();
                int total_money = 0;
                    //binding.editTextNumberSigned.getText("editText1");
                TextView textView = (TextView) findViewById(R.id.show_money);
                EditText editText1 = (EditText) findViewById(R.id.editTextNumberSigned);
                EditText editText2 = (EditText) findViewById(R.id.editTextNumberSigned2);
                EditText editText3 = (EditText) findViewById(R.id.editTextNumberSigned3);
                EditText editText4 = (EditText) findViewById(R.id.editTextNumberSigned4);
                String money60 = editText1.getText().toString();
                String money70 = editText2.getText().toString();
                String money90 = editText3.getText().toString();
                String money80 = editText4.getText().toString();
                int q60 = Integer.valueOf(money60).intValue();
                int q70 = Integer.valueOf(money70).intValue();
                int q90 = Integer.valueOf(money90).intValue();
                int q80 = Integer.valueOf(money80).intValue();

                total_money = 60*q60 + 70*q70 + 80*q80 + 90*q90;
                String s = String.valueOf(total_money);
                textView.setText(s);
                break;
            }
        }
    }*/

    private final AdapterView.OnItemSelectedListener menuSpinner = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (position){
                case 0:{
                    //Toast.makeText(getContext(), "0", Toast.LENGTH_LONG).show();
                    binding.textView11.setText("美式咖啡 $60：");
                    binding.textView12.setText("無糖拿鐵 $70：");
                    binding.textView13.setText("卡布奇諾 $90：");
                    binding.textView14.setText("摩卡可可 $80：");
                    binding.showMoney.setText("");
                    binding.editTextNumberSigned.setText("");
                    binding.editTextNumberSigned2.setText("");
                    binding.editTextNumberSigned3.setText("");
                    binding.editTextNumberSigned4.setText("");
                    break;
                }
                case 1:{
                    //Toast.makeText(getContext(), "1", Toast.LENGTH_LONG).show();
                    binding.textView11.setText("紅　　茶 $60：");
                    binding.textView12.setText("綠　　茶 $70：");
                    binding.textView13.setText("檸檬紅茶 $90：");
                    binding.textView14.setText("柳橙綠茶 $80：");
                    binding.showMoney.setText("");
                    binding.editTextNumberSigned.setText("");
                    binding.editTextNumberSigned2.setText("");
                    binding.editTextNumberSigned3.setText("");
                    binding.editTextNumberSigned4.setText("");
                    break;
                }
                case 2:{
                    //Toast.makeText(getContext(), "2", Toast.LENGTH_LONG).show();
                    binding.textView11.setText("奶　　茶 $60：");
                    binding.textView12.setText("珍珠奶茶 $70：");
                    binding.textView13.setText("可可牛奶 $90：");
                    binding.textView14.setText("黑糖牛奶 $80：");
                    binding.showMoney.setText("");
                    binding.editTextNumberSigned.setText("");
                    binding.editTextNumberSigned2.setText("");
                    binding.editTextNumberSigned3.setText("");
                    binding.editTextNumberSigned4.setText("");
                    break;
                }
                case 3:{
                    //Toast.makeText(getContext(), "3", Toast.LENGTH_LONG).show();
                    binding.textView11.setText("草莓氣泡 $60：");
                    binding.textView12.setText("香蕉氣泡 $70：");
                    binding.textView13.setText("葡萄氣泡 $90：");
                    binding.textView14.setText("鳳梨氣泡 $80：");
                    binding.showMoney.setText("");
                    binding.editTextNumberSigned.setText("");
                    binding.editTextNumberSigned2.setText("");
                    binding.editTextNumberSigned3.setText("");
                    binding.editTextNumberSigned4.setText("");
                    break;
                }
                case 4:{
                    //Toast.makeText(getContext(), "4", Toast.LENGTH_LONG).show();
                    binding.textView11.setText("手工麵包 $60：");
                    binding.textView12.setText("草莓貝果 $70：");
                    binding.textView13.setText("藍莓貝果 $90：");
                    binding.textView14.setText("起司貝果 $80：");
                    binding.showMoney.setText("");
                    binding.editTextNumberSigned.setText("");
                    binding.editTextNumberSigned2.setText("");
                    binding.editTextNumberSigned3.setText("");
                    binding.editTextNumberSigned4.setText("");
                    break;
                }
                case 5:{
                    //Toast.makeText(getContext(), "5", Toast.LENGTH_LONG).show();
                    binding.textView11.setText("手工餅乾 $60：");
                    binding.textView12.setText("草莓蛋糕 $70：");
                    binding.textView13.setText("香蕉蛋糕 $90：");
                    binding.textView14.setText("可可蛋糕 $80：");
                    binding.showMoney.setText("");
                    binding.editTextNumberSigned.setText("");
                    binding.editTextNumberSigned2.setText("");
                    binding.editTextNumberSigned3.setText("");
                    binding.editTextNumberSigned4.setText("");
                    break;
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}