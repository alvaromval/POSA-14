package edu.vuum.mocca.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

import com.robotium.solo.Solo;

import edu.vuum.mocca.PingPongActivity;

/**
 * @class PingPongActivityTest
 *
 * @brief Implements a Robotium test for the PingPongActivity.
 */
public class PingPongActivityTest
       extends ActivityInstrumentationTestCase2<PingPongActivity> {
    /**
     * Constructor initializes the superclass.
     */
    public PingPongActivityTest() {
        super(PingPongActivity.class);
    }

    /**
     * This is the handle for Robotium, which allows us to interact
     * with the UI.
     */
    Solo mSolo;

    /**
     * The context of this project, not the target project.
     */
    Context mContext;

    Button playButton_;
    TextView outputTextView_;

    /**
     * Called before each test is run to perform the initialization.
     */
    public void setUp() throws Exception {
        super.setUp();

        // Setup Robotium and get the EditText View.
        mSolo = new Solo(getInstrumentation(), getActivity());

        mContext = getInstrumentation().getContext();

        playButton_ = (Button) mSolo.getView(edu.vuum.mocca.R.id.play_button);

        outputTextView_ = (TextView) mSolo
            .getView(edu.vuum.mocca.R.id.pingpong_output);

        getInstrumentation().callActivityOnStart(getActivity());
        getInstrumentation().callActivityOnResume(getActivity());
    }

    /**
     * No need for @Test because of use of
     * ActivityInstrumentationTestCase2 which makes each method in
     * this class a @Test method TODO find better explanation, 100%
     * proper.
     */
    public void testPlayPingPongButtonPress() throws Exception {
        Thread.sleep(TestOptions.ACCEPTABLE_STARTUP_LENGTH);

        // Asserttrue(outputTextView_.isAttachedToWindow() == true);
        assertTrue(outputTextView_.getText().length() == 0);

        // Click on the 'play' button
        mSolo.clickOnView(mSolo.getView(edu.vuum.mocca.R.id.play_button));

        // wait for the threads to execute
        Thread.sleep(TestOptions.ACCEPTABLE_RUNTIME_LENGTH);

        assertTrue(outputTextView_.getText().toString()
                   .equals("Ready...Set...Go!\nping    (1)\n_pong (1)\nping    (2)\n_pong (2)\nping    (3)\n_pong (3)\nping    (4)\n_pong (4)\nping    (5)\n_pong (5)\nping    (6)\n_pong (6)\nping    (7)\n_pong (7)\nping    (8)\n_pong (8)\nping    (9)\n_pong (9)\nping    (10)\n_pong (10)\nDone!\n"));
    }
}
