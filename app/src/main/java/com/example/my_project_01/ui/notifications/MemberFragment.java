package com.example.my_project_01.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_project_01.R;
import com.example.my_project_01.databinding.FragmentMemberBinding;
import com.example.my_project_01.ui.dashboard.BuyList_inStyle;

public class MemberFragment extends Fragment {

    private FragmentMemberBinding binding;
ImageView mem_buy_cart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        MemberViewModel notificationsViewModel =
//                new ViewModelProvider(this).get(MemberViewModel.class);

        binding = FragmentMemberBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mem_buy_cart=root.findViewById(R.id.mem_buy_cart);
        mem_buy_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BuyCart.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private String[] func = {"餘額查詢", "交易明細", "最新消息", "投資理財", "離開"};
    private int[] c = {R.drawable.toxic, R.drawable.toxic, R.drawable.toxic, R.drawable.toxic, R.drawable.toxic};
    ListView member_lv;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        member_lv = view.findViewById(R.id.member_list);
        MyMemberAdapter adapter = new MyMemberAdapter();
        member_lv.setAdapter(adapter);
        member_lv.setOnItemClickListener(listener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }

    class MyMemberAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return func.length;
        }

        @Override
        public Object getItem(int i) {
            return func[i];
        }

        @Override
        public long getItemId(int i) {
            return c[i];
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View r = view;
            if (r == null) {
                r = getLayoutInflater().inflate(R.layout.member_style, null);
                ImageView member_img = r.findViewById(R.id.member_img);
                TextView member_tv = r.findViewById(R.id.member_tv);
                member_img.setImageResource(c[i]);
                member_tv.setText(func[i]);
            }
            return r;
        }
    }

    ;
    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {


            switch (i){
                case 0:
                Toast.makeText(getActivity(), "查詢餘額", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(),MemberBalance.class);
                    startActivity(intent);
                        break;
                case 1:
                    Toast.makeText(getActivity(), "交易明細", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getActivity(), "最新消息", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getActivity(), "投資理財", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(getActivity(), "離開", Toast.LENGTH_SHORT).show();
                    break;
            }



        }
    };

}
