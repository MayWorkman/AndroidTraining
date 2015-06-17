package course.examples.modernart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


/**
 * Created by May Workman on 6/7/2015.
 */
public class MoreInfoDialog extends DialogFragment {

    public static MoreInfoDialog newInstance(int title) {
        MoreInfoDialog frag = new MoreInfoDialog();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.more_info_title);
        builder.setMessage(R.string.more_info_subtitle);
        builder.setPositiveButton(R.string.visit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((MainActivity)getActivity()).doPositiveClick();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((MainActivity)getActivity()).doNegativeClick();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
