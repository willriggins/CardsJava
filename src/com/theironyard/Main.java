package com.theironyard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {

    static HashSet<Card> createDeck() {
        HashSet<Card> deck = new HashSet<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card c = new Card(suit, rank);
                deck.add(c);
            }
        }
        return deck;
    }

    static HashSet<HashSet<Card>> createHands(HashSet<Card> deck) {
        //loop over deck, then loop over it again. will have four layers of for loops
        HashSet<HashSet<Card>> hands = new HashSet<>();
        for (Card c1 : deck) {
            HashSet<Card> deck2 = (HashSet<Card>) deck.clone();
            deck2.remove(c1);
            for (Card c2 : deck2) {
                HashSet<Card> deck3 = (HashSet<Card>) deck2.clone();
                deck3.remove(c2);
                for (Card c3 : deck3) {
                    HashSet<Card> deck4 = (HashSet<Card>) deck3.clone();
                    deck4.remove(c3);
                    for (Card c4 : deck4) {
                        HashSet<Card> hand = new HashSet<>();
                        hand.add(c1);
                        hand.add(c2);
                        hand.add(c3);
                        hand.add(c4);
                        hands.add(hand);
                    }
                }
            }
        }
        return hands;
    }

    static boolean isFlush(HashSet<Card> hand) {
        HashSet<Card.Suit> suits = hand.stream()
                .map(card -> card.suit)
                .collect(Collectors.toCollection(HashSet<Card.Suit>::new));
        return suits.size() == 1;
    }

    static boolean isFourOfAKind(HashSet<Card> hand) {
        HashSet<Card.Rank> ranks = hand.stream()
                .map(card -> card.rank)
                .collect(Collectors.toCollection(HashSet<Card.Rank>::new));
        return ranks.size() == 1;
    }

    // was this supposed to be a stream?
    static boolean isStraight(HashSet<Card> hand) {
        boolean straight = false;
        if (hand.contains(Card.Rank.ACE) && hand.contains(Card.Rank.TWO) && hand.contains(Card.Rank.THREE) && hand.contains(Card.Rank.FOUR)) {
            straight = true;
        }
        if (hand.contains(Card.Rank.TWO) && hand.contains(Card.Rank.THREE) && hand.contains(Card.Rank.FOUR) && hand.contains(Card.Rank.FIVE)) {
            straight = true;
        }
        if (hand.contains(Card.Rank.THREE) && hand.contains(Card.Rank.FOUR) && hand.contains(Card.Rank.FIVE) && hand.contains(Card.Rank.SIX)) {
            straight = true;
        }
        if (hand.contains(Card.Rank.FOUR) && hand.contains(Card.Rank.FIVE) && hand.contains(Card.Rank.SIX) && hand.contains(Card.Rank.SEVEN)) {
            straight = true;
        }
        if (hand.contains(Card.Rank.FIVE) && hand.contains(Card.Rank.SIX) && hand.contains(Card.Rank.SEVEN) && hand.contains(Card.Rank.EIGHT)) {
            straight = true;
        }
        if (hand.contains(Card.Rank.SIX) && hand.contains(Card.Rank.SEVEN) && hand.contains(Card.Rank.EIGHT) && hand.contains(Card.Rank.NINE)) {
            straight = true;
        }
        if (hand.contains(Card.Rank.SEVEN) && hand.contains(Card.Rank.EIGHT) && hand.contains(Card.Rank.NINE) && hand.contains(Card.Rank.TEN)) {
            straight = true;
        }
        if (hand.contains(Card.Rank.EIGHT) && hand.contains(Card.Rank.NINE) && hand.contains(Card.Rank.TEN) && hand.contains(Card.Rank.JACK)) {
            straight = true;
        }
        if (hand.contains(Card.Rank.NINE) && hand.contains(Card.Rank.TEN) && hand.contains(Card.Rank.JACK) && hand.contains(Card.Rank.QUEEN)) {
            straight = true;
        }
        if (hand.contains(Card.Rank.TEN) && hand.contains(Card.Rank.JACK) && hand.contains(Card.Rank.QUEEN) && hand.contains(Card.Rank.KING)) {
            straight = true;
        }
        return straight;
    }

    public static void main(String[] args) {
        HashSet<Card> deck = createDeck();
        HashSet<HashSet<Card>> hands = createHands(deck);

        // create flushes
        HashSet<HashSet<Card>> flushes = hands.stream()
                .filter(Main::isFlush)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        // create straights
        HashSet<HashSet<Card>> straights = hands.stream()
                .filter(Main::isStraight)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        // create straight flush ? ans = 40?
        HashSet<HashSet<Card>> straightFlushes = hands.stream()
                .filter(Main::isFlush)
                .filter(Main::isStraight)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        // create 4 of a kind
        HashSet<HashSet<Card>> fours = hands.stream()
                .filter(Main::isFourOfAKind)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));



        System.out.println(straights.size());
    }
}
