package com.brim.FotterFragmnet;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.brim.Adapter.ListAdapter_Offer;
import com.brim.Adapter.RecentTransactionAdapter;
import com.brim.ApiHelper.HTTP_Get;
import com.brim.AppContant.ApiConstant;
import com.brim.BaseActivity;
import com.brim.Font.AxiformaBook;
import com.brim.Font.AxiformaMedium;
import com.brim.Font.AxiformaRegular;
import com.brim.Pojo.TransactionListData;
import com.brim.R;
import com.brim.Utils.AppProgerssDialog;
import com.brim.Utils.ConvertLocal;
import com.brim.Utils.DateFormatConvertion;
import com.brim.Utils.Loger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashBoard.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashBoard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashBoard extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ViewPager Top_Pager;
    LinearLayout Page_indicator;
    private ImageView[] dots;
    RecyclerView list_item;

    LinkedList<String> ListData;
    LinkedList<TransactionListData> RecentTransactionList;



    RecyclerView Rec_Recent_Transcation;

    AppProgerssDialog appProgerssDialog;

    private OnFragmentInteractionListener mListener;

    public DashBoard() {




    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashBoard.
     */
    // TODO: Rename and change types and number of parameters
    public static DashBoard newInstance(String param1, String param2) {
        DashBoard fragment = new DashBoard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseActivity)getActivity()).ButoomChnager(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        appProgerssDialog=new AppProgerssDialog(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Top_Pager=(ViewPager)view.findViewById(R.id.Top_Pager);
        Page_indicator=(LinearLayout)view.findViewById(R.id.Page_indicator);
        Rec_Recent_Transcation=(RecyclerView) view.findViewById(R.id.Rec_Recent_Transcation);
        list_item=(RecyclerView)view.findViewById(R.id.list_item);
        list_item.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        Rec_Recent_Transcation.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));


        new HTTP_Get(ApiConstant.CARDS) {


            @Override
            protected void OnSucess(String Response) {
                try {

                    JSONObject Object=new JSONObject(Response);

                    CallToAddView(Object.getString("id"));
                    ((AxiformaBook)view.findViewById(R.id.TXT_Balance)).setText("$"+ ConvertLocal.priceWithDecimal(Double.parseDouble(Object.getString("current_balance"))));
                    ((AxiformaMedium)view.findViewById(R.id.TXT_1)).setText("$"+ConvertLocal.priceWithDecimal(Double.parseDouble(Object.getString("credit_available"))));
                    ((AxiformaMedium)view.findViewById(R.id.TXT_2)).setText("$"+ConvertLocal.priceWithDecimal(Double.parseDouble(Object.getString("minimum_payment"))));
                    ((AxiformaMedium)view.findViewById(R.id.TXT_3)).setText("$"+ConvertLocal.priceWithDecimal(Double.parseDouble(Object.getString("last_payment_amount"))));
                    ((AxiformaRegular)view.findViewById(R.id.TXT_Min_Pay_Due_Date)).setText("due "+ DateFormatConvertion.yyyymmdd_to_mmmddyyyy(Object.getString("minimum_payment_due_date")));
                    ((AxiformaRegular)view.findViewById(R.id.TXT_LAST_PAID)).setText("paid "+ DateFormatConvertion.yyyymmdd_to_mmmddyyyy(Object.getString("last_payment_date")));
                    ((AxiformaRegular)view.findViewById(R.id.TXT_Limit)).setText("limit $"+ ConvertLocal.priceWithOutDecimal(Double.parseDouble(Object.getString("credit_limit"))));


                    if(Object.has("current_points_balance_in_dollars")) {
                        ((AxiformaRegular) view.findViewById(R.id.TXT_PointL)).setText("$" + ConvertLocal.priceWithDecimal(Double.parseDouble(Object.getString("current_points_balance_in_dollars"))));
                        ((AxiformaMedium) view.findViewById(R.id.TXT_4)).setText(ConvertLocal.priceWithOutDecimal(Double.parseDouble(Object.getString("current_points_balance")))+" pts");
                    }else {
                        ((AxiformaMedium) view.findViewById(R.id.TXT_4)).setText("NA");
                        ((AxiformaRegular) view.findViewById(R.id.TXT_PointL)).setText("");
                    }

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            protected void OnErrorApi(String Error) {
                Loger.MSG("@@ ON Erroe",Error);
            }

            @Override
            protected void OnHttPError(String HttpError) {

            }
        };

//        Top_Pager.setAdapter(new BookingPagerAdapter(getActivity().getSupportFragmentManager()));
//        Top_Pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                for (int i = 0; i < 1; i++) {
//                    dots[i].setImageResource(R.drawable.default_dot);
//                }
//                dots[position].setImageResource(R.drawable.selected_dot);
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        setUiPageViewController(1);

        ListData=new LinkedList<>();



        for(int i=0;i<10;i++)
        {
            ListData.add("tttt");
        }

        list_item.setAdapter(new ListAdapter_Offer(getActivity(),ListData));











    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void CallToAddView(String id) {
        appProgerssDialog.SetTitle(getString(R.string.app_name));
        appProgerssDialog.SetMessage("Please Wait...");
        appProgerssDialog.Show();

        new HTTP_Get(ApiConstant.TRANSACTION+id+"/transactions?card_filter=primary&page_size=1000") {
            @Override
            protected void OnSucess(String Response) {
                RecentTransactionList=new LinkedList<>();
                try {
                    JSONObject JSobject=new JSONObject(Response);
                    JSONArray transactions=JSobject.getJSONArray("transactions");

                    for (int i=0;i<transactions.length();i++)
                    {
                        TransactionListData datat=new TransactionListData();
                        datat.setItemObject(transactions.getJSONObject(i));
                        switch (transactions.getJSONObject(i).getJSONObject("category").getString("id"))
                        {
                            case "1000":
                                datat.setImage(R.drawable.icon_clothing);
                                break;
                            case "1001":
                                datat.setImage(R.drawable.icon_grocery);
                                break;
                            case "1002":
                                datat.setImage(R.drawable.icon_helth);
                                break;

                            case "1003":
                                datat.setImage(R.drawable.icon_furniture);
                                break;
                            case "1004":
                                datat.setImage(R.drawable.icon_transport);
                                break;
                            case "1005":
                                datat.setImage(R.drawable.icon_shoping);
                                break;

                            case "1006":
                                datat.setImage(R.drawable.icon_food);
                                break;


                        }
                        RecentTransactionList.add(datat);


                    }

                    Rec_Recent_Transcation.setAdapter(new RecentTransactionAdapter(getActivity(),RecentTransactionList));
                    Rec_Recent_Transcation.setNestedScrollingEnabled(false);

                    appProgerssDialog.Dismiss();



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            protected void OnErrorApi(String Error) {

            }

            @Override
            protected void OnHttPError(String HttpError) {

            }
        };


    }


}
