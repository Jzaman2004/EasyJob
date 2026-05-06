package com.mycompany.easyjob;

public interface MatchListener {
    void onSkip(Job job);
    void onInterested(Job job);
}