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
import android.os.Handler;
import android.os.Build.VERSION;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.FrameLayout.LayoutParams;

public class SwipeView extends FrameLayout {
    private View mFocusedView;
    private View mFocusedViewLike;
    private View mFocusedViewNope;
    private int mFocusedViewWidth;
    private float mPreviousAlpha = 0.0F;
    private Integer mLikeResource = Integer.valueOf(0);
    private Integer mNopeResource = Integer.valueOf(0);
    private static final int MAX_ELEMENTS = 8;
    private static final long DELAY_SCROLL_RUNNABLE = 1L;
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
    private SwipeView.ScrollMode mScrollModeX;
    private SwipeView.ScrollMode mScrollModeY;
    private SwipeView.ScrollDirection mScrollDirection;
    private int[] paddingX;
    private int[] paddingYTop;
    private int[] paddingYBottom;
    private SwipeView.OnCardSwipedListener mOnCardSwipedListener;
    private Handler mScrollHandler;
    private Runnable mScrollRunnable;
    private final SimpleOnGestureListener simpleOnGestureListener;

    public SwipeView(Context context, Integer likeResource, Integer nopeResource, SwipeView.OnCardSwipedListener cardSwipeListener) {
        super(context);
        this.mScrollModeX = SwipeView.ScrollMode.NONE;
        this.mScrollModeY = SwipeView.ScrollMode.NONE;
        this.mScrollDirection = SwipeView.ScrollDirection.NONE;
        this.paddingX = new int[]{0, 10, 20};
        this.paddingYTop = new int[]{0, 10, 20};
        this.paddingYBottom = new int[]{20, 10, 0};
        this.mScrollHandler = new Handler();
        this.mScrollRunnable = new Runnable() {
            public void run() {
                boolean scrollX;
                boolean scrollY;
                int scrollX1;
                int scrollY1;
                if (SwipeView.this.mScrollDirection == SwipeView.ScrollDirection.OUT) {
                    if (SwipeView.this.mNeedToScrollX <= 0 && SwipeView.this.mNeedToScrollY <= 0) {
                        SwipeView.this.mScrollHandler.removeCallbacks(SwipeView.this.mScrollRunnable);
                        SwipeView.this.removeView(SwipeView.this.mFocusedView);
                        if (SwipeView.this.mScrollModeX == SwipeView.ScrollMode.LEFT) {
                            SwipeView.this.mOnCardSwipedListener.onLikes();
                        } else if (SwipeView.this.mScrollModeX == SwipeView.ScrollMode.RIGHT) {
                            SwipeView.this.mOnCardSwipedListener.onDisLikes();
                        }

                        SwipeView.this.alignCardsPadding();
                    } else {
                        if (SwipeView.this.mNeedToScrollX < SwipeView.this.mScrollLengthX) {
                            SwipeView.this.mScrollLengthX = SwipeView.this.mNeedToScrollX;
                            SwipeView.this.mNeedToScrollX = 0;
                        } else {
                            SwipeView.this.mNeedToScrollX = SwipeView.this.mNeedToScrollX - SwipeView.this.mScrollLengthX;
                        }

                        if (SwipeView.this.mNeedToScrollY < SwipeView.this.mScrollLengthY) {
                            SwipeView.this.mScrollLengthY = SwipeView.this.mNeedToScrollY;
                            SwipeView.this.mNeedToScrollY = 0;
                        } else {
                            SwipeView.this.mNeedToScrollY = SwipeView.this.mNeedToScrollY - SwipeView.this.mScrollLengthY;
                        }

                        scrollX = false;
                        scrollY = false;
                        if (SwipeView.this.mScrollModeX == SwipeView.ScrollMode.LEFT) {
                            scrollX1 = -SwipeView.this.mScrollLengthX;
                        } else {
                            scrollX1 = SwipeView.this.mScrollLengthX;
                        }

                        if (SwipeView.this.mScrollModeY == SwipeView.ScrollMode.TOP) {
                            scrollY1 = -SwipeView.this.mScrollLengthY;
                        } else {
                            scrollY1 = SwipeView.this.mScrollLengthY;
                        }

                        SwipeView.this.mFocusedView.scrollBy(scrollX1, scrollY1);
                        SwipeView.this.mScrollHandler.postDelayed(SwipeView.this.mScrollRunnable, 1L);
                    }
                } else if (SwipeView.this.mScrollDirection == SwipeView.ScrollDirection.IN) {
                    if (SwipeView.this.mTotalScrolledX <= 0 && SwipeView.this.mTotalScrolledY <= 0) {
                        SwipeView.this.mScrollHandler.removeCallbacks(SwipeView.this.mScrollRunnable);
                        SwipeView.this.mScrollDirection = SwipeView.ScrollDirection.NONE;
                    } else {
                        if (SwipeView.this.mTotalScrolledX < SwipeView.this.mScrollLengthX) {
                            SwipeView.this.mScrollLengthX = SwipeView.this.mTotalScrolledX;
                            SwipeView.this.mTotalScrolledX = 0;
                        } else {
                            SwipeView.this.mTotalScrolledX = SwipeView.this.mTotalScrolledX - SwipeView.this.mScrollLengthX;
                        }

                        if (SwipeView.this.mTotalScrolledY < SwipeView.this.mScrollLengthY) {
                            SwipeView.this.mScrollLengthY = SwipeView.this.mTotalScrolledY;
                            SwipeView.this.mTotalScrolledY = 0;
                        } else {
                            SwipeView.this.mTotalScrolledY = SwipeView.this.mTotalScrolledY - SwipeView.this.mScrollLengthY;
                        }

                        scrollX = false;
                        scrollY = false;
                        if (SwipeView.this.mScrollModeX == SwipeView.ScrollMode.LEFT) {
                            scrollX1 = SwipeView.this.mScrollLengthX;
                        } else {
                            scrollX1 = -SwipeView.this.mScrollLengthX;
                        }

                        if (SwipeView.this.mScrollModeY == SwipeView.ScrollMode.TOP) {
                            scrollY1 = -SwipeView.this.mScrollLengthY;
                        } else {
                            scrollY1 = SwipeView.this.mScrollLengthY;
                        }

                        SwipeView.this.mFocusedView.scrollBy(scrollX1, scrollY1);
                        SwipeView.this.mScrollHandler.postDelayed(SwipeView.this.mScrollRunnable, 1L);
                    }
                }

            }
        };
        this.simpleOnGestureListener = new SimpleOnGestureListener() {
            public boolean onSingleTapConfirmed(MotionEvent e) {
                SwipeView.this.mOnCardSwipedListener.onSingleTap();
                return super.onSingleTapConfirmed(e);
            }

            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (SwipeView.this.mFocusedView != null) {
                    SwipeView.this.mScrolledPixelsX = SwipeView.this.mScrolledPixelsX + (int) distanceX;
                    SwipeView.this.mScrolledPixelsY = SwipeView.this.mScrolledPixelsY + (int) distanceY;
                    SwipeView.this.mFocusedView.scrollBy((int) distanceX, (int) distanceY);
                    float alpha = (float) SwipeView.this.mScrolledPixelsX / (float) SwipeView.this.mFocusedViewWidth;
                    if (alpha > 0.0F) {
                        SwipeView.this.mFocusedViewNope.setVisibility(View.VISIBLE);
                        SwipeView.this.mFocusedViewLike.setVisibility(View.GONE);
                        SwipeView.setAlpha(SwipeView.this.mFocusedViewNope, SwipeView.this.mPreviousAlpha, alpha);
                        SwipeView.this.mPreviousAlpha = alpha;
                    } else {
                        SwipeView.this.mFocusedViewNope.setVisibility(View.GONE);
                        SwipeView.this.mFocusedViewLike.setVisibility(View.VISIBLE);
                        SwipeView.setAlpha(SwipeView.this.mFocusedViewLike, SwipeView.this.mPreviousAlpha, -alpha);
                        SwipeView.this.mPreviousAlpha = -alpha;
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

        for (int gestureDetector = 0; gestureDetector < this.paddingX.length; ++gestureDetector) {
            this.paddingX[gestureDetector] = (int) ((float) this.paddingX[gestureDetector] * density);
            this.paddingYTop[gestureDetector] = (int) ((float) this.paddingYTop[gestureDetector] * density);
            this.paddingYBottom[gestureDetector] = (int) ((float) this.paddingYBottom[gestureDetector] * density);
        }

        final GestureDetector var7 = new GestureDetector(this.mContext, this.simpleOnGestureListener);
        this.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (SwipeView.this.getChildCount() > 0) {
                    if (SwipeView.this.mScrollDirection != SwipeView.ScrollDirection.NONE) {
                        return false;
                    } else if (!SwipeView.this.enableTouchSwipe) {
                        return false;
                    } else {
                        var7.onTouchEvent(event);
                        switch (event.getAction()) {
                            case 0:
                                if (SwipeView.this.getChildCount() > 0) {
                                    SwipeView.this.mFocusedView = SwipeView.this.getChildAt(SwipeView.this.getChildCount() - 1);
                                    SwipeView.this.mFocusedViewLike = SwipeView.this.mFocusedView.findViewById(SwipeView.this.mLikeResource.intValue());
                                    SwipeView.this.mFocusedViewNope = SwipeView.this.mFocusedView.findViewById(SwipeView.this.mNopeResource.intValue());
                                    SwipeView.this.mFocusedViewWidth = SwipeView.this.mFocusedView.getWidth();
                                    SwipeView.this.mFocusedView.setPadding(0,0,0,0);
                                }

                                SwipeView.this.resetScrollingValues();
                                break;
                            case 1:
                                SwipeView.this.alignCardsPadding();
                                if (SwipeView.this.mScrolledPixelsX < 0) {
                                    SwipeView.this.mScrollModeX = SwipeView.ScrollMode.LEFT;
                                    SwipeView.this.mTotalScrolledX = -SwipeView.this.mScrolledPixelsX;
                                } else {
                                    SwipeView.this.mScrollModeX = SwipeView.ScrollMode.RIGHT;
                                    SwipeView.this.mTotalScrolledX = SwipeView.this.mScrolledPixelsX;
                                }

                                if (SwipeView.this.mScrolledPixelsY < 0) {
                                    SwipeView.this.mScrollModeY = SwipeView.ScrollMode.BOTTOM;
                                    SwipeView.this.mTotalScrolledY = -SwipeView.this.mScrolledPixelsY;
                                } else {
                                    SwipeView.this.mScrollModeY = SwipeView.ScrollMode.TOP;
                                    SwipeView.this.mTotalScrolledY = SwipeView.this.mScrolledPixelsY;
                                }

                                SwipeView.this.detectSwipe();
                        }

                        return true;
                    }
                } else {
                    return false;
                }
            }
        });
    }

    public void addCard(View view, int position) {
        if (this.getChildCount() <= 8 && position < 8) {
            LinearLayout viewLayout = new LinearLayout(this.mContext);
            viewLayout.setLayoutParams(new LayoutParams(-1, -1));
            view.setLayoutParams(new LayoutParams(-1, -1));
            viewLayout.addView(view);
            viewLayout.setPadding(5,5,5,5);
            this.addView(viewLayout, 0);
        }

    }

    public void removeFocusedCard() {
        this.removeView(this.mFocusedView);
        this.alignCardsPadding();
    }

    private void alignCardsPadding() {
        int i = 0;

        for (int j = this.getChildCount() - 1; j >= 0; --j) {
            this.getChildAt(j).setPadding(5,5,5,5);
            ++i;
        }

        this.mScrollDirection = SwipeView.ScrollDirection.NONE;
    }

    private void resetScrollingValues() {
        this.mPreviousAlpha = 0.0F;
        this.mNeedToScrollX = 0;
        this.mScrolledPixelsX = 0;
        this.mTotalScrolledX = 0;
        this.mNeedToScrollY = 0;
        this.mScrolledPixelsY = 0;
        this.mTotalScrolledY = 0;
        this.mScrollLengthX = 5;
        this.mScrollLengthY = 5;
        this.mScrollModeX = SwipeView.ScrollMode.NONE;
        this.mScrollModeY = SwipeView.ScrollMode.NONE;
    }

    public void resetFocuedView() {
        if (this.getChildCount() > 0) {
            View mFocusedView = this.getChildAt(this.getChildCount() - 1);
            View mFocusedViewLike = mFocusedView.findViewById(this.mLikeResource.intValue());
            View mFocusedViewNope = mFocusedView.findViewById(this.mNopeResource.intValue());
            setAlpha(mFocusedViewLike, 0.0F, 0.0F);
            setAlpha(mFocusedViewNope, 0.0F, 0.0F);
            mFocusedView.scrollTo(0, 0);
        }

    }

    private void detectSwipe() {
        int imageHalf = this.mFocusedView.getWidth() / 2;
        this.mNeedToScrollX = this.mFocusedView.getWidth() - this.mTotalScrolledX;
        if (this.mScrollDirection == SwipeView.ScrollDirection.NONE) {
            if (this.mNeedToScrollX < imageHalf) {
                this.mScrollDirection = SwipeView.ScrollDirection.OUT;
            } else {
                this.mScrollDirection = SwipeView.ScrollDirection.IN;
                setAlpha(this.mFocusedViewLike, 0.0F, 0.0F);
                setAlpha(this.mFocusedViewNope, 0.0F, 0.0F);
            }
        }

        this.mScrollHandler.post(this.mScrollRunnable);
    }

    public void likeCard() {
        if (this.getChildCount() > 0) {
            this.mFocusedView = this.getChildAt(this.getChildCount() - 1);
            this.mFocusedViewLike = this.mFocusedView.findViewById(this.mLikeResource.intValue());
            this.mFocusedViewNope = this.mFocusedView.findViewById(this.mNopeResource.intValue());
            if (this.mScrollDirection != SwipeView.ScrollDirection.NONE) {
                return;
            }

            this.resetScrollingValues();
            this.mScrollDirection = SwipeView.ScrollDirection.OUT;
            this.mScrollModeX = SwipeView.ScrollMode.LEFT;
            this.mFocusedViewLike.setVisibility(View.VISIBLE);
            setAlpha(this.mFocusedViewLike, 0.0F, 1.0F);
            this.detectSwipe();
        }

    }

    public void dislikeCard() {
        if (this.getChildCount() > 0) {
            this.mFocusedView = this.getChildAt(this.getChildCount() - 1);
            this.mFocusedViewLike = this.mFocusedView.findViewById(this.mLikeResource.intValue());
            this.mFocusedViewNope = this.mFocusedView.findViewById(this.mNopeResource.intValue());
            if (this.mScrollDirection != SwipeView.ScrollDirection.NONE) {
                return;
            }

            this.resetScrollingValues();
            this.mScrollDirection = SwipeView.ScrollDirection.OUT;
            this.mScrollModeX = SwipeView.ScrollMode.RIGHT;
            this.mFocusedViewNope.setVisibility(View.VISIBLE);
            setAlpha(this.mFocusedViewNope, 0.0F, 1.0F);
            this.detectSwipe();
        }

    }

    public void setTouchable(boolean touchable) {
        this.enableTouchSwipe = touchable;
    }

    public static void setAlpha(View view, float fromAlpha, float toAlpha) {
        if (VERSION.SDK_INT < 11) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
            alphaAnimation.setDuration(0L);
            alphaAnimation.setFillAfter(true);
            view.startAnimation(alphaAnimation);
        } else {
            view.setAlpha(toAlpha);
        }

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