package actions;

import java.sql.SQLException;

import command.Action;
import state.Game;

public class TextOnly implements Updater {
	public static String name = "textOnly";

	@Override
	public void update(Game g, Action a) throws SQLException {
		String s = a.getName();
		switch (s) {
		case "jump on bed":
			if (g.here().equals("1")) {
				g.setOutput("No one's looking. Why not? You jump on the bed. Wheeeeee!");
			} else {
				g.setOutput("There's no bed here.");
			}
			break;
		case "sleep in bed":
			if (g.here().equals("1")) {
				g.setOutput(
						"A quick nap won't hurt anyone. <br> zzz <br> zzz <br> zzz <br> You wake up feeling refreshed.");
			} else {
				g.setOutput("There's no bed here.");
			}
			break;
		case "jump out window":
			if (g.here().equals("1")) {
				g.setOutput(
						"You look out the window first and remember you are on the second story. You decide this would be a bad idea.");
			} else {
				g.setOutput("What window?");
			}
			break;
		case "look in boxes":
			if (g.here().equals("2")) {
				g.setOutput(
						"You have been putting off unpacking these for months. You don't even remember what's in them at this point. You should have probably labled them. You'd need to unpack them to figure it out.");
			} else {
				g.setOutput("I don't know which boxes you are talking about.");
			}
			break;
		case "unpack boxes":
			if (g.here().equals("2")) {
				g.setOutput(
						"What's one more day of procrasinating when it comes to unpacking? You have no desire to start now.");
			} else {
				g.setOutput("I don't know which boxes you are talking about.");
			}
			break;
		case "appreciate painting":
			if (g.here().equals("3")) {
				g.setOutput(
						"You stare at the painting. You stare long and hard. Nope. You can't do it. You do not know what your dad sees in this monstrosity.");
			} else {
				g.setOutput("There's no painting here to appreciate.");
			}
			break;
		case "destroy painting":
			if (g.here().equals("3")) {
				g.setOutput("You really, really want to destroy the painting, but you couldn't do that to your dad.");
			} else {
				g.setOutput("There's no painting here to destroy.");
			}
			break;
		case "wash hands":
			if (g.here().equals("4")) {
				if (g.status.isSink()) {
					g.setOutput("You wash your hands. No viruses getting you today!");
				} else {
					g.setOutput("Kinda hard to wash your hands when you haven't turned the sink on...");
				}
			} else {
				g.setOutput("I'm not sure where you're planning on doing that...");
			}
			break;
		case "wash face":
			if (g.here().equals("4")) {
				if (g.status.isSink()) {
					g.setOutput("You wash your face. You feel refreshed.");
				} else {
					g.setOutput("Kinda hard to wash your face when you haven't turned the sink on...");
				}
			} else {
				g.setOutput("I'm not sure where you're planning on doing that...");
			}
			break;
		case "flush toilet":
			if (g.here().equals("4")) {
				g.setOutput("Wooooooosh!");
			} else {
				g.setOutput("I don't see any toilets here.");
			}
			break;
		case "use toilet":
			if (g.here().equals("4")) {
				g.setOutput("Aaalllriiiight. I'll just go over here and let you do that.");
			} else {
				g.setOutput("I don't see any toilets here.");
			}
			break;
		case "get in shower":
			if (g.here().equals("4")) {
				if (g.status.isShower()) {
					if (g.status.isClothes()) {
						g.setOutput("It would be weird to take a shower with your clothes on.");
					} else {
						g.setOutput(
								"It feels nice to let the water run over you. It's easy to let time pass in the shower.");
						g.status.setWet(true);
					}
				} else {
					g.setOutput("You stand in the shower. The water is off. Why are you in the shower?");
				}
			} else {
				g.setOutput("What shower?");
			}
			break;
		case "dry off":
			if (g.status.isWet()) {
				if (!g.inventory().hasItem("towel")) {
					g.setOutput("You need a towel to dry off.");
				} else {
					g.setOutput("You are now dry and clean. You feel great.");
					g.status.setWet(false);
				}
			} else {
				g.setOutput("How are you going to dry off if you aren't wet?");
			}
			break;
		case "sleep in couch":
			if (g.here().equals("6")) {
				g.setOutput("You stretch out on the couch and take a quick nap. You wake up feeling better.");
			} else {
				g.setOutput("No couch and no naps here!");
			}
			break;
		case "play piano":
			if (g.here().equals("7")) {
				g.setOutput("You fumble around the keys. Sound, but nothing really recognizable comes out.");
			} else {
				g.setOutput("You have a piano in the study, if you want to play later.");
			}
			break;
		case "eat apple":
			if (g.inventory().hasItem("apple")) {
				g.setOutput("You eat the apple. It has a satisfying crunch.");
				g.inventory().removeItem("apple");
			} else {
				g.setOutput("You can't eat something you don't have...");
			}
			break;
		case "read letter":
			if (g.inventory().hasItem("letter")) {
				g.setOutput(
						"You look at the envelope. It is addressed to you and the return address is... Your house? The sender is listed as your dad."
								+ "What game is he up to this time?. You open it, expecting a letter, but it seems to be an old ad with word-art quality graphics that says: <br> "
								+ "<strong>" + "WELCOME TO TGTAEM!" + "<br>"
								+ "TGTAEM is an adventure more than a game, but it will test your wits and cunning!"
								+ "In it, you will see things no mortal has ever seen before! No computer should be without one!!!1!"
								+ "</strong>" + "<br>"
								+ "At the bottom of the ad there is an arrow with your dad's handwriting: \"Read Back\" it says..."
								+ "<br>" + "You turn the paper over. There is a letter from you dad, which you read:"
								+ "<br>" + "<em>" + "Congratulations kiddo!" + "<br>"
								+ "If you are reading this, you are finally ready to begin your GRAND ADVENTURE."
								+ "I am so proud of you. I wanted to give you some final tips to make sure you just have a swell time:"
								+ "<br>"
								+ "-- You can GO in all cardinal directions: NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, and NORTHWEST. You can also GO UP and DOWN."
								+ "<br>"
								+ "-- To GO faster, you can just type N, NE, E, SE, S, SW, W, NW, U, and D, no GO necessary."
								+ "<br>"
								+ "-- Capitalization may give you important information, but you don't need to type it."
								+ "<br>" + "-- To see what you are holding, type CHECK INVENTORY or I for short."
								+ "<br>"
								+ "-- If you go down a hole without the aid of a ladder or the like, you might not be able to come back the way you came right away, if at all."
								+ "<br>" + "-- Sources of light are only useful if you turn them on." + "<br>"
								+ "-- You can TYPE on your laptop to keep notes." + "<br>"
								+ "-- If you have a key, it will automatically open its door when you GO through it."
								+ "<br>" + "There is more that you will discover along the way, kiddo. You'll do great."
								+ "<br>" + "Good Luck," + "<br>" + "Dad" + "</em>" + "<br>"
								+ "Well that was a lot. Some of it you definitely wish you had known " + g.status.getMove()
								+ " turns ago. Some of it made no sense at all."
								+ "But that's Dad for you. You put the letter back in your pocket.");
			} else {
				g.setOutput("How do you expect to read a letter you don't have?");
			}
			break;
		case "swing":
			if (g.here().equals("11")) {
				g.setOutput(
						"You push off the ground, kicking your legs out and getting your momentum going. Before long, it feels like gravity is a non-issue as you soar back and forth."
								+ "You forgot how much you loved to do this. Everything you are worrying about slips away. But you can't escape forever.... <br> <br> <br>"
								+ "After a while, you slow, and let gravity carry you back to earth and reality.");
			} else {
				g.setOutput("No swing here.");
			}
			break;
		case "swing sword":
			if (g.inventory().hasItem("sword")) {
				g.setOutput("<strong> SWIIIISHHH!!! <strong> Now that was satisfying.");
			} else {
				g.setOutput("You don't have a sword. It would be cool if you did.");
			}
			break;
		}

	}

}
