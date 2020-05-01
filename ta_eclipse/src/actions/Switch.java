package actions;

import java.sql.SQLException;

import command.Action;
import state.Game;

public class Switch implements Updater {
	public static String name = "switch";

//TODO Add Room checks where necessary
	@Override
	public void update(Game g, Action a) throws SQLException {
		String v = a.verb();
		String i = a.noun();
		switch (i) {
		case "laptop":
			if (g.inventory().hasItem("laptop")) {
				if (v.equalsIgnoreCase("turn on") || v.equalsIgnoreCase("open")) {
					if (g.s.laptop) {
						g.setOutput("The laptop is already open and running.");
					} else {
						g.setOutput("You open the laptop and turn it on. It hums to life and the screen glows.");
						g.s.laptop = true;
					}
				} else if (v.equalsIgnoreCase("turn off") || v.equalsIgnoreCase("close")) {
					if (g.s.laptop) {
						g.setOutput("You close the laptop.");
						g.s.laptop = false;
					} else {
						g.setOutput("The laptop is already off.");
					}
				}
			} else {
				g.setOutput("You don't have your laptop");
			}
			break;
		case "window":
			if (g.here().contentEquals("1")) {
				if (v.equalsIgnoreCase("open")) {
					if (g.s.window) {
						g.setOutput("The window is already open. A nice breeze is flowing into your room.");
					} else {
						g.setOutput("You open the window. A nice cool breeze starts to come in through the window.");
						g.s.window = true;
					}
				} else if (v.equalsIgnoreCase("close")) {
					if (g.s.window) {
						g.setOutput("You close the window. You were getting a bit chilly.");
						g.s.window = false;
					} else {
						g.setOutput("The window is already closed. It looks like a really nice day outside though.");
					}
				}
			} else {
				g.setOutput("What window?");
			}
			break;
		case "flashlight":
			if (g.inventory().hasItem("flashlight")) {
				if (v.equalsIgnoreCase("turn on")) {
					if (g.s.flashlight) {
						g.setOutput("The flashlight is already on.");
					} else {
						g.setOutput("You turn flashlight on.");
						g.s.flashlight = true;
					}
				} else if (v.equalsIgnoreCase("turn off")) {
					if (g.s.flashlight) {
						g.setOutput("You turn the flashlight off.");
						g.s.flashlight = false;
					} else {
						g.setOutput("It's hard to turn off something that's not even on...");
					}
				}
			} else {
				g.setOutput("You don't have the flashlight. You never know when you need a light source.");
			}
			break;
		case "sink":
			if (g.here().equals("4")) {
				if (v.equalsIgnoreCase("turn on")) {
					if (g.s.sink) {
						g.setOutput("The sink is already on. You better not be wasting water!");
					} else {
						g.setOutput("You turn the sink on. Water flows from the tap.");
						g.s.sink = true;
					}
				} else if (v.equalsIgnoreCase("turn off")) {
					if (g.s.sink) {
						g.setOutput(
								"You turn the sink off. It still drips a little. You should probably fix that some time.");
						g.s.sink = false;
					} else {
						g.setOutput(
								"Though it's dripping, you can't turn the knobs any more. It's as off as it's going to get.");
					}
				}
			} else {
				g.setOutput("There's no sink here");
			}
			break;
		case "shower":
			if (g.here().equals("4")) {
				if (v.equalsIgnoreCase("turn on")) {
					if (g.s.shower) {
						g.setOutput("The shower is already on. You test the temperature. Ahh, just how you like it.");
					} else {
						g.setOutput("You turn the shower on. FWOOSH! The water pressure is decent.");
						g.s.shower = true;
					}
				} else if (v.equalsIgnoreCase("turn off")) {
					if (g.s.shower) {
						g.setOutput("You turn off the shower.");
						g.s.shower = false;
					} else {
						g.setOutput("The shower is already off.");
					}
				}
			} else {
				g.setOutput("There's no shower here.");
			}
			break;
		case "undressed":
			if (g.s.clothes) {
				if (g.here().equals("4")) {
					g.setOutput(
							"Oh! Alright then. I hope you are doing this to take a shower. I'll just turn around while you do that.");
					g.s.clothes = false;
				} else {
					g.setOutput("This isn't really an appropriate place to do that.");
				}
			} else {
				g.setOutput("You already did that. Can't exactly do it again.");
			}
			break;
		case "dressed":
			if (g.s.clothes) {
				g.setOutput("Do you want to put <em> more </em> clothes on? Try WEARing them.");
			} else {
				if (g.s.wet) {
					g.setOutput("You are still wet. That would be weird.");
				} else {
					g.setOutput(
							"You put your clothes back on. Now that you are all showered up, let's get back to business.");
					g.s.clothes = true;
				}
			}
			break;
		case "TV":
			if (g.here().equals("6")) {
				if (g.inventory().hasItem("remote")) {
					if (v.equalsIgnoreCase("turn on")) {
						if (g.s.TV) {
							g.setOutput("The TV is already on.");
						} else {
							g.setOutput("You tap the power button on the remote. The TV whirrs to life.");
							g.s.TV = true;
						}
					} else if (v.equalsIgnoreCase("turn off")) {
						if (g.s.TV) {
							g.setOutput("You press the remote's power button. The TV turns off, leaving silence.");
							g.s.TV = false;
						} else {
							g.setOutput("The TV is already off.");
						}
					}
				} else {
					if (v.equalsIgnoreCase("turn on")) {
						if (g.s.TV) {
							g.setOutput("The TV is already on.");
						} else {
							g.setOutput(
									"You look around for the remote. Not finding it, you walk over to the TV and turn it on at the source.");
							g.s.TV = true;
						}
					} else if (v.equalsIgnoreCase("turn off")) {
						if (g.s.TV) {
							if (!g.s.searchCouch) {
								g.setOutput(
										"You should probably SEARCH for the remote, but you aren't sure where. You walk over and turn off the TV at the source.");
							} else {
								g.setOutput(
										"Now where did you put the remote? You walk over and turn off the TV at the source.");
							}
							g.s.TV = false;
						} else {
							g.setOutput("The TV is already off.");
						}
					}
				}
			} else {
				g.setOutput("There's no TV here.");
			}
			break;
		case "channels":
			// TODO Random number resulting in different channels.
			// Also changes TV item description
			if (g.here().equals("6")) {
				if (g.inventory().hasItem("remote")) {
					g.setOutput(
							"You try flipping through the channels, but you only seem to get your dad's buisness channel.");
				} else {
					g.setOutput("You need the remote to do that.");
				}
			} else {
				g.setOutput("Can't change the channels when there's no TV here.");
			}
			break;
		case "PC":
			if (g.here().equals("7")) {
				if (v.equalsIgnoreCase("turn on")) {
					if (g.s.PC) {
						g.setOutput("The PC is already on.");
					} else {
						g.setOutput("You tap the power button on the tower. The PC whirrs to life." + "<br>"
								+ "<strong> ENTER PASSWORD: </strong> displays on the screen." + "<br> <em>"
								+ "Use the command ENTER PASSWORD to enter the PC interface </em>");
						g.s.PC = true;
					}
				} else if (v.equalsIgnoreCase("turn off")) {
					if (g.s.PC) {
						g.setOutput("You press the power button and the PC shuts off.");
						g.s.PC = false;
					} else {
						g.setOutput("The PC is already off.");
					}
				}
			} else {
				g.setOutput("The PC is in the study. You are not.");
			}
			break;
		case "flag":
			if (g.here().equals("10")) {
				if (v.equalsIgnoreCase("raise")) {
					if (g.s.mailFlag) {
						g.setOutput("The flag on the mail box is already up.");
					} else {
						g.setOutput(
								"You push the flag to its upright position. Now the mail carrier will know there is mail waiting.");
						g.s.mailFlag = true;
					}
				} else if (v.equalsIgnoreCase("lower")) {
					if (g.s.mailFlag) {
						g.setOutput("You push the flag down. No mail today.");
						g.s.mailFlag = false;
					} else {
						g.setOutput("The mailbox flag is already down.");
					}
				}
			} else {
				g.setOutput("No flags here.");
			}
			break;
		}

	}

}
