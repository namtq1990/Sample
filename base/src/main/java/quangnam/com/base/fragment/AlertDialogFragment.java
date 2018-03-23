package quangnam.com.base.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import quangnam.com.base.R;
import quangnam.com.base.activity.BaseActivity;

/**
 * Created by quangnam on 8/28/17.
 * Project base
 */

@SuppressWarnings("unused")
public class AlertDialogFragment extends BaseDialog {
    public static final String TAG = AlertDialogFragment.class.getName();
    private static final String ARG_TITLE = "title";
    private static final String ARG_MESSAGE = "message";
    private static final String ARG_ACTION = "action";
    private static final String ARG_POSITIVE = "positive";
    private static final String ARG_NEUTRAL = "neutral";
    private static final String ARG_NEGATIVE = "negative";

    public static final int RET_DIALOG_SHOWN = -1;

    public static final int DEFAULT_ACTION = 0;
    public static final int ACTION_REQUEST_SIGN_IN = 10;

    private AlertDialogListener mListener;
    private final DialogInterface.OnClickListener mOnClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (mListener != null) {
                mListener.onDialogClick(dialog, which, getAction(), getArguments());
            }
        }
    };

    protected static void prepareArgument(final Bundle args,
                                        final int action,
                                        final String title,
                                        final String message,
                                        final String btnPosition,
                                        final String btnNeutral,
                                        final String btnNegative) {
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        args.putInt(ARG_ACTION, action);
        args.putString(ARG_POSITIVE, btnPosition);
        args.putString(ARG_NEUTRAL, btnNeutral);
        args.putString(ARG_NEGATIVE, btnNegative);
    }

    public static AlertDialogFragment newInstance(final int action,
                                                  final String title,
                                                  final String message,
                                                  final String btnPosition,
                                                  final String btnNeutral,
                                                  final String btnNegative) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle bundle = new Bundle();

        prepareArgument(bundle, action, title, message, btnPosition, btnNeutral, btnNegative);
        fragment.setArguments(bundle);

        return fragment;
    }

    public static AlertDialogFragment instantiate(final BaseActivity activity,
                                                  final int action,
                                                  final String title,
                                                  final String message,
                                                  final String btnPosition,
                                                  final String btnNeutral,
                                                  final String btnNegative) {
        AlertDialogFragment fragment = (AlertDialogFragment) activity.getSupportFragmentManager()
                .findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = newInstance(action, title, message, btnPosition, btnNeutral, btnNegative);
        } else if (fragment.getArguments() != null) {
            prepareArgument(fragment.getArguments(), action, title, message, btnPosition, btnNeutral, btnNegative);
        }

        return fragment;
    }

    public static AlertDialogFragment instantiate(final BaseActivity activity,
                                                  final int action,
                                                  @StringRes final int titleID,
                                                  @StringRes final int messageID,
                                                  @StringRes final int btnPosition,
                                                  @StringRes final int btnNeutral,
                                                  @StringRes final int btnNegative) {
        return instantiate(activity,
                action,
                activity.getString(titleID),
                activity.getString(messageID),
                activity.getString(btnPosition),
                activity.getString(btnNeutral),
                activity.getString(btnNegative));
    }

    public static void instantiateAndShow(final BaseActivity activity,
                                                         final int action,
                                                         final String title,
                                                         final String message,
                                                         final String btnPosition,
                                                         final String btnNeutral,
                                                         final String btnNegative) {
        AlertDialogFragment fragment = activity.getAlertDialog() != null
                ? activity.getAlertDialog()
                : (AlertDialogFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = newInstance(action, title, message, btnPosition, btnNeutral, btnNegative);
            fragment.show(activity.getSupportFragmentManager(), TAG);
            activity.setAlertDialog(fragment);
        } else {
            if (fragment.getArguments() != null)
                prepareArgument(fragment.getArguments(), action, title, message, btnPosition, btnNeutral, btnNegative);
            fragment.updateDialog();
        }

        activity.setAlertDialog(fragment);
    }

    public static void instantiateAndShow(final BaseActivity activity,
                                                         final int action,
                                                         @StringRes final int titleID,
                                                         @StringRes final int messageID,
                                                         @StringRes final int btnPosition,
                                                         @StringRes final int btnNeutral,
                                                         @StringRes final int btnNegative) {
        instantiateAndShow(activity,
                action,
                activity.getString(titleID),
                activity.getString(messageID),
                activity.getString(btnPosition),
                activity.getString(btnNeutral),
                activity.getString(btnNegative));
    }

    /**
     * Set interaction with this fragment, see {@link AlertDialogListener} for detail
     */
    public void setListener(AlertDialogListener listener) {
        mListener = listener;
    }

    @Override
    public void onDetach() {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.setAlertDialog(null);

        super.onDetach();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialog)
                .setMessage(getMessage())
                .setTitle(getTitle());
        if (getNeutralLabel() != null)
            builder.setNeutralButton(getNeutralLabel(), mOnClick);
        if (getPositiveLabel() != null) {
            builder.setPositiveButton(getPositiveLabel(), mOnClick);
        }
        if (getNegativeLabel() != null) {
            builder.setNegativeButton(getNegativeLabel(), mOnClick);
        }

        return builder.create();
    }

    public void updateDialog() {
        AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            updateButton(DialogInterface.BUTTON_NEGATIVE);
            updateButton(DialogInterface.BUTTON_NEUTRAL);
            updateButton(DialogInterface.BUTTON_POSITIVE);

            dialog.setTitle(getTitle());
            dialog.setMessage(getMessage());
        }
    }

    public String getTitle() {
        return getArguments().getString(ARG_TITLE);
    }

    public String getMessage() {
        return getArguments().getString(ARG_MESSAGE);
    }

    /**
     * @return The action that define for dialog, example close, open, ... Default is 0
     */
    public int getAction() {
        return getArguments().getInt(ARG_ACTION);
    }

    public String getPositiveLabel() {
        return getArguments().getString(ARG_POSITIVE);
    }

    public String getNegativeLabel() {
        return getArguments().getString(ARG_NEGATIVE);
    }

    public String getNeutralLabel() {
        return getArguments().getString(ARG_NEUTRAL);
    }

    public String getButtonLabel(int which) {
        switch (which) {
            case DialogInterface.BUTTON_NEGATIVE:
                return getNegativeLabel();
            case DialogInterface.BUTTON_NEUTRAL:
                return getNeutralLabel();
            case DialogInterface.BUTTON_POSITIVE:
                return getPositiveLabel();
            default:
                return null;
        }
    }

    public void updateButton(int which) {
        AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            String text = getButtonLabel(which);
            Button button = dialog.getButton(which);

            if (button != null) {
                button.setText(text);
                button.setVisibility(text == null ? View.GONE : View.VISIBLE);
            }
        }
    }

    /**
     * interface to interaction with other component, should be set by {@link #setListener(AlertDialogListener)}
     * in {@link android.support.v7.app.AppCompatActivity#onAttachFragment(Fragment)}
     * or {@link BaseFragment#onAttachFragment(Fragment)}.
     * <BR>
     * Current Action:
     * <br>
     * - {@link #onDialogClick(DialogInterface, int, int, Bundle)}
     */
    @SuppressWarnings("WeakerAccess")
    public interface AlertDialogListener {
        /**
         * Handle all action click of the dialog
         *
         * @param dialog Current dialog
         * @param which  int: the button that was clicked (ex. {@link DialogInterface#BUTTON_POSITIVE}) or the position of the item clicked
         * @param action int: Define action to specify which dialog send it
         * @param args   Other argument that send to this dialog
         */
        void onDialogClick(DialogInterface dialog, int which, int action, Bundle args);
    }
}
