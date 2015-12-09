/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Handler
 *  android.util.DisplayMetrics
 *  android.view.GestureDetector
 *  android.view.GestureDetector$OnGestureListener
 *  android.view.GestureDetector$SimpleOnGestureListener
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.LinearLayout
 */
package com.algorepublic.matzomatch;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class SwipeView
        extends FrameLayout {
    private View mFocusedView;
    private View mFocusedViewLike;
    private View mFocusedViewNope;
    private int mFocusedViewWidth;
    private float mPreviousAlpha = 0.0f;
    private Integer mLikeResource = 0;
    private Integer mNopeResource = 0;
    private static final int MAX_ELEMENTS = 3;
    private static final long DELAY_SCROLL_RUNNABLE = 1;
    private static final int SCROLL_LENGTH = 5;
    private int mScrolledPixelsX;
    private int mScrolledPixelsY;
    private int mNeedToScrollX;
    private int mNeedToScrollY;
    private int mTotalScrolledX;
    private int mTotalScrolledY;
    private int mScrollLengthX = 5;
    private int mScrollLengthY = 5;
    private boolean enableTouchSwipe = true;
    private Context mContext;
    private ScrollMode mScrollModeX = ScrollMode.NONE;
    private ScrollMode mScrollModeY = ScrollMode.NONE;
    private ScrollDirection mScrollDirection = ScrollDirection.NONE;
    private int[] paddingX;
    private int[] paddingYTop;
    private int[] paddingYBottom;
    private OnCardSwipedListener mOnCardSwipedListener;
    private Handler mScrollHandler;
    private Runnable mScrollRunnable;
    private final GestureDetector.SimpleOnGestureListener simpleOnGestureListener;

    public SwipeView(Context context, Integer likeResource, Integer nopeResource, OnCardSwipedListener cardSwipeListener) {
        super(context);
        int[] arrn = new int[3];
        arrn[1] = 10;
        arrn[2] = 20;
        this.paddingX = arrn;
        int[] arrn2 = new int[3];
        arrn2[1] = 10;
        arrn2[2] = 20;
        this.paddingYTop = arrn2;
        int[] arrn3 = new int[3];
        arrn3[0] = 20;
        arrn3[1] = 10;
        this.paddingYBottom = arrn3;
        this.mScrollHandler = new Handler();
        this.mScrollRunnable = new Runnable(){

            @Override
            public void run() {
                if (SwipeView.this.mScrollDirection == ScrollDirection.OUT) {
                    if (SwipeView.this.mNeedToScrollX > 0 || SwipeView.this.mNeedToScrollY > 0) {
                        if (SwipeView.this.mNeedToScrollX < SwipeView.this.mScrollLengthX) {
                            SwipeView.access$4(SwipeView.this, SwipeView.this.mNeedToScrollX);
                            SwipeView.access$5(SwipeView.this, 0);
                        } else {
                            SwipeView swipeView = SwipeView.this;
                            SwipeView.access$5(swipeView, swipeView.mNeedToScrollX - SwipeView.this.mScrollLengthX);
                        }
                        if (SwipeView.this.mNeedToScrollY < SwipeView.this.mScrollLengthY) {
                            SwipeView.access$7(SwipeView.this, SwipeView.this.mNeedToScrollY);
                            SwipeView.access$8(SwipeView.this, 0);
                        } else {
                            SwipeView swipeView = SwipeView.this;
                            SwipeView.access$8(swipeView, swipeView.mNeedToScrollY - SwipeView.this.mScrollLengthY);
                        }
                        int scrollX = 0;
                        int scrollY = 0;
                        scrollX = SwipeView.this.mScrollModeX == ScrollMode.LEFT ? - SwipeView.this.mScrollLengthX : SwipeView.this.mScrollLengthX;
                        scrollY = SwipeView.this.mScrollModeY == ScrollMode.TOP ? - SwipeView.this.mScrollLengthY : SwipeView.this.mScrollLengthY;
                        SwipeView.this.mFocusedView.scrollBy(scrollX, scrollY);
                        SwipeView.this.mScrollHandler.postDelayed(SwipeView.this.mScrollRunnable, 1);
                    } else {
                        SwipeView.this.mScrollHandler.removeCallbacks(SwipeView.this.mScrollRunnable);
                        SwipeView.this.removeView(SwipeView.this.mFocusedView);
                        if (SwipeView.this.mScrollModeX == ScrollMode.LEFT) {
                            SwipeView.this.mOnCardSwipedListener.onLikes();
                        } else if (SwipeView.this.mScrollModeX == ScrollMode.RIGHT) {
                            SwipeView.this.mOnCardSwipedListener.onDisLikes();
                        }
                        SwipeView.this.alignCardsPadding();
                    }
                } else if (SwipeView.this.mScrollDirection == ScrollDirection.IN) {
                    if (SwipeView.this.mTotalScrolledX > 0 || SwipeView.this.mTotalScrolledY > 0) {
                        if (SwipeView.this.mTotalScrolledX < SwipeView.this.mScrollLengthX) {
                            SwipeView.access$4(SwipeView.this, SwipeView.this.mTotalScrolledX);
                            SwipeView.access$18(SwipeView.this, 0);
                        } else {
                            SwipeView swipeView = SwipeView.this;
                            SwipeView.access$18(swipeView, swipeView.mTotalScrolledX - SwipeView.this.mScrollLengthX);
                        }
                        if (SwipeView.this.mTotalScrolledY < SwipeView.this.mScrollLengthY) {
                            SwipeView.access$7(SwipeView.this, SwipeView.this.mTotalScrolledY);
                            SwipeView.access$19(SwipeView.this, 0);
                        } else {
                            SwipeView swipeView = SwipeView.this;
                            SwipeView.access$19(swipeView, swipeView.mTotalScrolledY - SwipeView.this.mScrollLengthY);
                        }
                        int scrollX = 0;
                        int scrollY = 0;
                        scrollX = SwipeView.this.mScrollModeX == ScrollMode.LEFT ? SwipeView.this.mScrollLengthX : - SwipeView.this.mScrollLengthX;
                        scrollY = SwipeView.this.mScrollModeY == ScrollMode.TOP ? - SwipeView.this.mScrollLengthY : SwipeView.this.mScrollLengthY;
                        SwipeView.this.mFocusedView.scrollBy(scrollX, scrollY);
                        SwipeView.this.mScrollHandler.postDelayed(SwipeView.this.mScrollRunnable, 1);
                    } else {
                        SwipeView.this.mScrollHandler.removeCallbacks(SwipeView.this.mScrollRunnable);
                        SwipeView.access$20(SwipeView.this, ScrollDirection.NONE);
                    }
                }
            }
        };
        this.simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener(){

            public boolean onSingleTapConfirmed(MotionEvent e) {
                SwipeView.this.mOnCardSwipedListener.onSingleTap();
                return super.onSingleTapConfirmed(e);
            }

            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (SwipeView.this.mFocusedView != null) {
                    SwipeView swipeView = SwipeView.this;
                    SwipeView.access$22(swipeView, swipeView.mScrolledPixelsX + (int)distanceX);
                    SwipeView swipeView2 = SwipeView.this;
                    SwipeView.access$24(swipeView2, swipeView2.mScrolledPixelsY + (int)distanceY);
                    SwipeView.this.mFocusedView.scrollBy((int)distanceX, (int)distanceY);
                    float alpha = (float)SwipeView.this.mScrolledPixelsX / (float)SwipeView.this.mFocusedViewWidth;
                    if (alpha > 0.0f) {
                        SwipeView.this.mFocusedViewNope.setVisibility(VISIBLE);
                        SwipeView.this.mFocusedViewLike.setVisibility(GONE);
                        SwipeView.setAlpha(SwipeView.this.mFocusedViewNope, SwipeView.this.mPreviousAlpha, alpha);
                        SwipeView.access$29(SwipeView.this, alpha);
                    } else {
                        SwipeView.this.mFocusedViewNope.setVisibility(GONE);
                        SwipeView.this.mFocusedViewLike.setVisibility(VISIBLE);
                        SwipeView.setAlpha(SwipeView.this.mFocusedViewLike, SwipeView.this.mPreviousAlpha, - alpha);
                        SwipeView.access$29(SwipeView.this, - alpha);
                    }
                }
                return true;
            }
        };
        this.mContext = context;
        this.mLikeResource = likeResource;
        this.mNopeResource = nopeResource;
        this.mOnCardSwipedListener = cardSwipeListener;
        float density = this.getResources().getDisplayMetrics().density;
        int i = 0;
        while (i < this.paddingX.length) {
            this.paddingX[i] = (int)((float)this.paddingX[i] * density);
            this.paddingYTop[i] = (int)((float)this.paddingYTop[i] * density);
            this.paddingYBottom[i] = (int)((float)this.paddingYBottom[i] * density);
            ++i;
        }
        final GestureDetector gestureDetector = new GestureDetector(this.mContext, (GestureDetector.OnGestureListener)this.simpleOnGestureListener);
        this.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View v, MotionEvent event) {
                if (SwipeView.this.getChildCount() > 0) {
                    if (SwipeView.this.mScrollDirection != ScrollDirection.NONE) {
                        return false;
                    }
                    if (!SwipeView.this.enableTouchSwipe) {
                        return false;
                    }
                    gestureDetector.onTouchEvent(event);
                    switch (event.getAction()) {
                        case 0: {
                            if (SwipeView.this.getChildCount() > 0) {
                                SwipeView.access$31(SwipeView.this, SwipeView.this.getChildAt(SwipeView.this.getChildCount() - 1));
                                SwipeView.access$33(SwipeView.this, SwipeView.this.mFocusedView.findViewById(SwipeView.this.mLikeResource.intValue()));
                                SwipeView.access$35(SwipeView.this, SwipeView.this.mFocusedView.findViewById(SwipeView.this.mNopeResource.intValue()));
                                SwipeView.access$36(SwipeView.this, SwipeView.this.mFocusedView.getWidth());
                                SwipeView.this.mFocusedView.setPadding(SwipeView.this.paddingX[0], 0, SwipeView.this.paddingX[0], 0);
                            }
                            SwipeView.this.resetScrollingValues();
                            break;
                        }
                        case 1: {
                            SwipeView.this.alignCardsPadding();
                            if (SwipeView.this.mScrolledPixelsX < 0) {
                                SwipeView.access$39(SwipeView.this, ScrollMode.LEFT);
                                SwipeView.access$18(SwipeView.this, - SwipeView.this.mScrolledPixelsX);
                            } else {
                                SwipeView.access$39(SwipeView.this, ScrollMode.RIGHT);
                                SwipeView.access$18(SwipeView.this, SwipeView.this.mScrolledPixelsX);
                            }
                            if (SwipeView.this.mScrolledPixelsY < 0) {
                                SwipeView.access$40(SwipeView.this, ScrollMode.BOTTOM);
                                SwipeView.access$19(SwipeView.this, - SwipeView.this.mScrolledPixelsY);
                            } else {
                                SwipeView.access$40(SwipeView.this, ScrollMode.TOP);
                                SwipeView.access$19(SwipeView.this, SwipeView.this.mScrolledPixelsY);
                            }
                            SwipeView.this.detectSwipe();
                            break;
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void addCard(View view, int position) {
        if (this.getChildCount() <= 3 && position < 3) {
            LinearLayout viewLayout = new LinearLayout(this.mContext);
            viewLayout.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
            view.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
            viewLayout.addView(view);
            viewLayout.setPadding(this.paddingX[position], this.paddingYTop[position], this.paddingX[position], this.paddingYBottom[position]);
            this.addView((View)viewLayout, 0);
        }
    }

    public void removeFocusedCard() {
        this.removeView(this.mFocusedView);
        this.alignCardsPadding();
    }

    private void alignCardsPadding() {
        int i = 0;
        int j = this.getChildCount() - 1;
        while (j >= 0) {
            this.getChildAt(j).setPadding(this.paddingX[i], this.paddingYTop[i], this.paddingX[i], this.paddingYBottom[i]);
            ++i;
            --j;
        }
        this.mScrollDirection = ScrollDirection.NONE;
    }

    private void resetScrollingValues() {
        this.mPreviousAlpha = 0.0f;
        this.mNeedToScrollX = 0;
        this.mScrolledPixelsX = 0;
        this.mTotalScrolledX = 0;
        this.mNeedToScrollY = 0;
        this.mScrolledPixelsY = 0;
        this.mTotalScrolledY = 0;
        this.mScrollLengthX = 5;
        this.mScrollLengthY = 5;
        this.mScrollModeX = ScrollMode.NONE;
        this.mScrollModeY = ScrollMode.NONE;
    }

    public void resetFocuedView() {
        if (this.getChildCount() > 0) {
            View mFocusedView = this.getChildAt(this.getChildCount() - 1);
            View mFocusedViewLike = mFocusedView.findViewById(this.mLikeResource.intValue());
            View mFocusedViewNope = mFocusedView.findViewById(this.mNopeResource.intValue());
            SwipeView.setAlpha(mFocusedViewLike, 0.0f, 0.0f);
            SwipeView.setAlpha(mFocusedViewNope, 0.0f, 0.0f);
            mFocusedView.scrollTo(0, 0);
        }
    }

    private void detectSwipe() {
        int imageHalf = this.mFocusedView.getWidth() / 2;
        this.mNeedToScrollX = this.mFocusedView.getWidth() - this.mTotalScrolledX;
        if (this.mScrollDirection == ScrollDirection.NONE) {
            if (this.mNeedToScrollX < imageHalf) {
                this.mScrollDirection = ScrollDirection.OUT;
            } else {
                this.mScrollDirection = ScrollDirection.IN;
                SwipeView.setAlpha(this.mFocusedViewLike, 0.0f, 0.0f);
                SwipeView.setAlpha(this.mFocusedViewNope, 0.0f, 0.0f);
            }
        }
        this.mScrollHandler.post(this.mScrollRunnable);
    }

    public void likeCard() {
        if (this.getChildCount() > 0) {
            this.mFocusedView = this.getChildAt(this.getChildCount() - 1);
            this.mFocusedViewLike = this.mFocusedView.findViewById(this.mLikeResource.intValue());
            this.mFocusedViewNope = this.mFocusedView.findViewById(this.mNopeResource.intValue());
            if (this.mScrollDirection != ScrollDirection.NONE) {
                return;
            }
            this.resetScrollingValues();
            this.mScrollDirection = ScrollDirection.OUT;
            this.mScrollModeX = ScrollMode.LEFT;
            this.mFocusedViewLike.setVisibility(VISIBLE);
            SwipeView.setAlpha(this.mFocusedViewLike, 0.0f, 1.0f);
            this.detectSwipe();
        }
    }

    public void dislikeCard() {
        if (this.getChildCount() > 0) {
            this.mFocusedView = this.getChildAt(this.getChildCount() - 1);
            this.mFocusedViewLike = this.mFocusedView.findViewById(this.mLikeResource.intValue());
            this.mFocusedViewNope = this.mFocusedView.findViewById(this.mNopeResource.intValue());
            if (this.mScrollDirection != ScrollDirection.NONE) {
                return;
            }
            this.resetScrollingValues();
            this.mScrollDirection = ScrollDirection.OUT;
            this.mScrollModeX = ScrollMode.RIGHT;
            this.mFocusedViewNope.setVisibility(VISIBLE);
            SwipeView.setAlpha(this.mFocusedViewNope, 0.0f, 1.0f);
            this.detectSwipe();
        }
    }

    public void setTouchable(boolean touchable) {
        this.enableTouchSwipe = touchable;
    }

    public static void setAlpha(View view, float fromAlpha, float toAlpha) {
        if (Build.VERSION.SDK_INT < 11) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            view.startAnimation((Animation)alphaAnimation);
        } else {
            view.setAlpha(toAlpha);
        }
    }

    static /* synthetic */ void access$4(SwipeView swipeView, int n) {
        swipeView.mScrollLengthX = n;
    }

    static /* synthetic */ void access$5(SwipeView swipeView, int n) {
        swipeView.mNeedToScrollX = n;
    }

    static /* synthetic */ void access$7(SwipeView swipeView, int n) {
        swipeView.mScrollLengthY = n;
    }

    static /* synthetic */ void access$8(SwipeView swipeView, int n) {
        swipeView.mNeedToScrollY = n;
    }

    static /* synthetic */ void access$18(SwipeView swipeView, int n) {
        swipeView.mTotalScrolledX = n;
    }

    static /* synthetic */ void access$19(SwipeView swipeView, int n) {
        swipeView.mTotalScrolledY = n;
    }

    static /* synthetic */ void access$20(SwipeView swipeView, ScrollDirection scrollDirection) {
        swipeView.mScrollDirection = scrollDirection;
    }

    static /* synthetic */ void access$22(SwipeView swipeView, int n) {
        swipeView.mScrolledPixelsX = n;
    }

    static /* synthetic */ void access$24(SwipeView swipeView, int n) {
        swipeView.mScrolledPixelsY = n;
    }

    static /* synthetic */ void access$29(SwipeView swipeView, float f) {
        swipeView.mPreviousAlpha = f;
    }

    static /* synthetic */ void access$31(SwipeView swipeView, View view) {
        swipeView.mFocusedView = view;
    }

    static /* synthetic */ void access$33(SwipeView swipeView, View view) {
        swipeView.mFocusedViewLike = view;
    }

    static /* synthetic */ void access$35(SwipeView swipeView, View view) {
        swipeView.mFocusedViewNope = view;
    }

    static /* synthetic */ void access$36(SwipeView swipeView, int n) {
        swipeView.mFocusedViewWidth = n;
    }

    static /* synthetic */ void access$39(SwipeView swipeView, ScrollMode scrollMode) {
        swipeView.mScrollModeX = scrollMode;
    }

    static /* synthetic */ void access$40(SwipeView swipeView, ScrollMode scrollMode) {
        swipeView.mScrollModeY = scrollMode;
    }


    public interface OnCardSwipedListener {
        void onLikes();

        void onDisLikes();

        void onSingleTap();
    }

    private static enum ScrollDirection {
        IN,
        OUT,
        NONE;

        private ScrollDirection() {
        }
    }

    private static enum ScrollMode {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        NONE;

        private ScrollMode() {
        }
    }
}