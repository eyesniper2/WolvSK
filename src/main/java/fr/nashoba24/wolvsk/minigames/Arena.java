package fr.nashoba24.wolvsk.minigames;

import fr.nashoba24.wolvsk.WolvSK;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;

public class Arena {
	
	private String arenaname;
	private Minigame game;
	//private Location lobby;
	private Integer[] lobbyCoos;
	private String lobbyWorld;
	private Integer minp;
	private Integer maxp;
	private ArrayList<String> pl = new ArrayList<String>();
	private boolean started = false;
	private Integer defaulttimer = 120;
	private Integer countdown = 120;
	private ArrayList<Block> signs = new ArrayList<Block>();

	public Arena(Minigame mg, String name, Integer min, Integer max) {
		name = name.replaceAll(" ", "-");
		game = mg;
		arenaname = name;
		minp = min;
		maxp = max;
	}
	
	public void setLobby(Location loc, boolean save) {
		lobbyCoos = new Integer[]{(int) loc.getX(), (int) loc.getY(), (int) loc.getZ()};
		lobbyWorld = loc.getWorld().getName();
		if(save) {
			Minigames.save(this.getMinigame());
		}
	}
	
	public void setLobby(Integer[] coos, String world, boolean save) {
		lobbyCoos = coos;
		lobbyWorld = world;
		if(save) {
			Minigames.save(this.getMinigame());
		}
	}
	
	public Location getLobby() {
		if(lobbyCoos!=null && lobbyWorld!=null) {
			World world = Bukkit.getWorld(lobbyWorld);
			if(lobbyCoos.length==3 && world!=null) {
				return new Location(world, (double) lobbyCoos[0], (double) lobbyCoos[1], (double) lobbyCoos[2]);
			}
		}
		return null;
	}
	
	public Minigame getMinigame() {
		return game;
	}
	
	public String getName() {
		return arenaname;
	}
	
	public void setMax(Integer max, boolean save) {
		maxp = max;
		if(save) {
			Minigames.save(this.getMinigame());
		}
		this.updateSigns();
	}
	
	public void setMin(Integer min, boolean save) {
		minp = min;
		if(save) {
			Minigames.save(this.getMinigame());
		}
	}
	
	public Integer getMin() {
		return minp;
	}
	
	public Integer getMax() {
		return maxp;
	}
	
	public void addPlayer(Player p) {
		pl.add(p.getName());
	}
	
	public void removePlayer(Player p) {
		pl.remove(p.getName());
	}
	
	public Integer playersCount() {
		return pl.size();
	}
	
	public boolean isInArena(Player p) {
		if(pl.contains(p.getName())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isStarted() {
		return started;
	}
	
	public void setDefaultTimer(Integer i, boolean save) {
		if(countdown==defaulttimer) {
			countdown = i;
			defaulttimer = i;
		}
		else {
			defaulttimer = i;
		}
		if(save) {
			Minigames.save(this.getMinigame());
		}
	}
	
	public Integer getDefaultTimer() {
		return defaulttimer;
	}
	
	public void finish() {
		if(this.isStarted()) {
			if(this.playersCount()==0 || (this.playersCount()==1 && this.getMin()>1)) {
				Minigames.stop(this.getMinigame(), this);
				countdown = defaulttimer;
			}
		}
	}
	
	public void countdown() {
		if(this.isStarted()) {
			return;
		}
		if(this.playersCount()>=this.getMin()) {
			if(countdown<0) {
				countdown = defaulttimer;
			}
			--countdown;
			if(countdown==0) {
				boolean s = Minigames.start(this.getMinigame(), this, false);
				if(!s) {
					countdown = defaulttimer - 1;
				}
			}
			Player[] list = this.getAllPlayers();
			for(Player p : list) {
				p.setExp(countdown.floatValue()/defaulttimer.floatValue());
				p.setLevel(countdown);
			}
			if(countdown!=defaulttimer) {
				if(countdown>=30) {
					if(countdown % 30 == 0) {
						this.broadcast(Minigames.getMessage(Minigames.xSecsLeft.replaceAll("%secs%", countdown.toString()), this.getMinigame().getPrefix(), false));
					}
				}
				else {
					if(countdown==20) {
						this.broadcast(Minigames.getMessage(Minigames.xSecsLeft.replaceAll("%secs%", countdown.toString()), this.getMinigame().getPrefix(), false));
					}
					else if(countdown==10) {
						this.broadcast(Minigames.getMessage(Minigames.xSecsLeft.replaceAll("%secs%", countdown.toString()), this.getMinigame().getPrefix(), false));
					}
					else if(countdown==5) {
						this.broadcast(Minigames.getMessage(Minigames.xSecsLeft.replaceAll("%secs%", countdown.toString()), this.getMinigame().getPrefix(), false));
						Player[] list2 = this.getAllPlayers();
						for(Player p : list2) {
							TitleAPI.sendTitle(p, 5, 10, 5, ChatColor.GREEN + "5", "");
						}
					}
					else if(countdown==4) {
						this.broadcast(Minigames.getMessage(Minigames.xSecsLeft.replaceAll("%secs%", countdown.toString()), this.getMinigame().getPrefix(), false));
						Player[] list2 = this.getAllPlayers();
						for(Player p : list2) {
							TitleAPI.sendTitle(p, 5, 10, 5, ChatColor.YELLOW + "4", "");
						}
					}
					else if(countdown==3) {
						this.broadcast(Minigames.getMessage(Minigames.xSecsLeft.replaceAll("%secs%", countdown.toString()), this.getMinigame().getPrefix(), false));
						Player[] list2 = this.getAllPlayers();
						for(Player p : list2) {
							TitleAPI.sendTitle(p, 5, 10, 5, ChatColor.GOLD + "3", "");
						}
					}
					else if(countdown==2) {
						this.broadcast(Minigames.getMessage(Minigames.xSecsLeft.replaceAll("%secs%", countdown.toString()), this.getMinigame().getPrefix(), false));
						Player[] list2 = this.getAllPlayers();
						for(Player p : list2) {
							TitleAPI.sendTitle(p, 5, 10, 5, ChatColor.RED + "2", "");
						}
					}
					else if(countdown==1) {
						this.broadcast(Minigames.getMessage(Minigames.OneSecLeft, this.getMinigame().getPrefix(), false));
						Player[] list2 = this.getAllPlayers();
						for(Player p : list2) {
							TitleAPI.sendTitle(p, 5, 10, 5, ChatColor.DARK_RED + "1", "");
						}
					}
				}
			}
		}
		else {
			countdown = defaulttimer;
			Player[] list = this.getAllPlayers();
			for(Player p : list) {
				p.setExp(countdown/defaulttimer - 0.01F);
				p.setLevel(countdown);
			}
		}
		Bukkit.getServer().getPluginManager().callEvent(new ArenaCountdownEvent(this.game, this, countdown));
	}
	
	public Player[] getAllPlayers() {
		if(pl.size()==0) {
			return new Player[]{};
		}
		ArrayList<Player> arr = new ArrayList<Player>();
		for(String s : pl) {
			arr.add(WolvSK.getInstance().getServer().getPlayer(s));
		}
		Player[] list = new Player[arr.size()];
		list = arr.toArray(list);
		return list;
	}
	
	public Integer getCountdown() {
		return countdown;
	}
	
	public void setStarted(boolean b) {
		started = b;
	}
	
	public void broadcast(String msg) {
		Player[] list = this.getAllPlayers();
		for(Player p : list) {
			p.sendMessage(msg);
		}
	}
	
	
	public Block[] getAllSigns() {
		if(signs.size()==0) {
			return new Block[]{};
		}
		Block[] list = new Block[signs.size()];
		list = signs.toArray(list);
		return list;
	}
	
	public void addSign(Block b, boolean save) {
		signs.add(b);
		this.updateSigns();
		if(save) {
			Minigames.save(this.getMinigame());
		}
	}
	
	public void removeSign(Block b, boolean save) {
		signs.remove(b);
		if(save) {
			Minigames.save(this.getMinigame());
		}
	}
	
	public void chat(Player p, String msg) {
		Player[] list = this.getAllPlayers();
		String m = Minigames.chatFormat.replaceAll("%player%", p.getName()).replaceAll("%message%", msg).replaceAll("%minigame%", this.getMinigame().getPrefix()).replaceAll("%arena%", this.getName());
		for(Player pl : list) {
			pl.sendMessage(m);
		}
	}
	
	public void updateSigns() {
		final Arena arena = this;
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(WolvSK.getInstance(), new Runnable() {
            @Override
            public void run() {
        		Block[] listb = arena.getAllSigns();
        		for(Block b : listb) {
        			if(b.getState() instanceof Sign) {
        				Sign sign = (Sign) b.getState();
        				if(sign.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "[" + arena.getMinigame().getPrefix() + "]")) {
        					String line2 = sign.getLine(1);
        					if(arena.getMinigame().getArena(line2, false)!=null) {
        						if(arena.getMinigame().getArena(line2, false)==arena) {
        							if(!arena.isStarted()) {
        								if(arena.playersCount()>=arena.getMin()) {
        									sign.setLine(2, ChatColor.DARK_GREEN + arena.playersCount().toString() + "/" + arena.getMax());
        								}
        								else {
        									sign.setLine(2, ChatColor.BLUE + arena.playersCount().toString() + "/" + arena.getMax());
        								}
        								sign.setLine(3, ChatColor.GREEN + "join");
        							}
        							else {
        								sign.setLine(2,  ChatColor.DARK_GREEN + arena.playersCount().toString() + "/" + arena.getMax());
        								sign.setLine(3, ChatColor.GOLD + "started");
        							}
        							sign.update();
        						}
        					}
        					else {
        						arena.removeSign(b, true);
        					}
        				}
        				else {
        					arena.removeSign(b, true);
        				}
        			}
        			else {
        				arena.removeSign(b, true);
        			}
        		}
        	}
        }, 1L);
	}
	
	public void setCountdown(Integer i) {
		this.countdown = i;
	}
}
