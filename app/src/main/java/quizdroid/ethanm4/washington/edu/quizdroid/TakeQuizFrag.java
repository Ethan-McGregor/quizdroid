package quizdroid.ethanm4.washington.edu.quizdroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;


public class TakeQuizFrag extends Fragment {

    private OnFragmentInteractionListener mListener;
    private String answer;
    private String correctAnswer;
    private int rightOrWrong;
    public TakeQuizFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_take_quiz, container, false);
        rightOrWrong = getArguments().getInt("count");
        correctAnswer = getArguments().getString("right");
        RadioButton rOne = (RadioButton) view.findViewById(R.id.aOne);
        RadioButton rTwo = (RadioButton) view.findViewById(R.id.aTwo);
        RadioButton rThree = (RadioButton) view.findViewById(R.id.aThree);
        RadioButton rFour = (RadioButton) view.findViewById(R.id.aFour);
        TextView qOne = (TextView) view.findViewById(R.id.qOne);


        String question = getArguments().getString("question");

        String one = getArguments().getString("one");
        String two = getArguments().getString("two");

        String three = getArguments().getString("three");
        String four = getArguments().getString("four");

        qOne.setText(question);
        rOne.setText(one);
        rTwo.setText(two);
        rThree.setText(three);
        rFour.setText(four);
        
View.OnClickListener buttonListener = new View.OnClickListener() {
    RadioButton rOne = (RadioButton) view.findViewById(R.id.aOne);
    RadioButton rTwo = (RadioButton) view.findViewById(R.id.aTwo);
    RadioButton rThree = (RadioButton) view.findViewById(R.id.aThree);
    RadioButton rFour = (RadioButton) view.findViewById(R.id.aFour);

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.aOne:

                if (rTwo.isChecked() || rThree.isChecked() || rFour.isChecked()) {
                    rTwo.setChecked(false);
                    rThree.setChecked(false);
                    rFour.setChecked(false);
                }
                rOne.setChecked(true);
                answer = rOne.getText() + "";
                break;
            case R.id.aTwo:

                if (rOne.isChecked() || rThree.isChecked() || rFour.isChecked()) {
                    rOne.setChecked(false);
                    rThree.setChecked(false);
                    rFour.setChecked(false);
                }
                rTwo.setChecked(true);
                answer = rTwo.getText() + "";

                break;
            case R.id.aThree:

                if (rTwo.isChecked() || rOne.isChecked() || rFour.isChecked()) {
                    rTwo.setChecked(false);
                    rOne.setChecked(false);
                    rFour.setChecked(false);
                }
                rThree.setChecked(true);
                answer = rThree.getText() + "";
                break;
            case R.id.aFour:

                if (rTwo.isChecked() || rThree.isChecked() || rOne.isChecked()) {
                    rTwo.setChecked(false);
                    rThree.setChecked(false);
                    rOne.setChecked(false);
                }
                rFour.setChecked(true);
                answer = rFour.getText() + "";
                break;
        }
    }
};

        view.findViewById(R.id.aOne).setOnClickListener(buttonListener);
        view.findViewById(R.id.aTwo).setOnClickListener(buttonListener);
        view.findViewById(R.id.aThree).setOnClickListener(buttonListener);
        view.findViewById(R.id.aFour).setOnClickListener(buttonListener);

        return view;
    }


    public String getAnswer(){
        return answer;
    }
    public String getCorrectAnswer(){
        return correctAnswer;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
