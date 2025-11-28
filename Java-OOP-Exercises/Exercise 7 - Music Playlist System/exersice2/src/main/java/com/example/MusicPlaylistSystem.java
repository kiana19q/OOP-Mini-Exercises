
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MusicPlaylistSystem {
    
    static class Song {
        private String title;
        private String artist;
        private int duration;
        private String genre;
        
        public Song(String title, String artist, int duration, String genre) {
            this.title = title;
            this.artist = artist;
            this.duration = duration;
            this.genre = genre;
        }
        
        public String getFormattedDuration() {
            int minutes = duration / 60;
            int seconds = duration % 60;
            return String.format("%02d:%02d", minutes, seconds);
        }
        
        public String getSongInfo() {
            return String.format("🎵 %s - %s (%s) [%s]", title, artist, getFormattedDuration(), genre);
        }
        
        public String getTitle() { return title; }
        public String getArtist() { return artist; }
        public int getDuration() { return duration; }
        public String getGenre() { return genre; }
        
        public void setTitle(String title) { this.title = title; }
        public void setArtist(String artist) { this.artist = artist; }
        public void setDuration(int duration) { this.duration = duration; }
        public void setGenre(String genre) { this.genre = genre; }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Song song = (Song) obj;
            return Objects.equals(title, song.title) && Objects.equals(artist, song.artist);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(title, artist);
        }
        
        @Override
        public String toString() {
            return getSongInfo();
        }
    }
    
    static class Playlist {
        private String name;
        private ArrayList<Song> songs;
        
        public Playlist(String name) {
            this.name = name;
            this.songs = new ArrayList<>();
        }
        
        public String addSong(Song song) {
            if (songs.contains(song)) {
                return "this song already exists.";
            }
            songs.add(song);
            return "added successfully.";
        }
        
        public int getTotalDuration() {
            int totalSeconds = 0;
            for (Song song : songs) {
                totalSeconds += song.getDuration();
            }
            return totalSeconds;
        }
        
        public String getFormattedTotalDuration() {
            int totalSeconds = getTotalDuration();
            int hours = totalSeconds / 3600;
            int minutes = (totalSeconds % 3600) / 60;
            int seconds = totalSeconds % 60;
            
            if (hours > 0) {
                return String.format("%02d:%02d:%02d", hours, minutes, seconds);
            } else {
                return String.format("%02d:%02d", minutes, seconds);
            }
        }
        
        public List<Song> findSongsByArtist(String artist) {
            List<Song> result = new ArrayList<>();
            for (Song song : songs) {
                if (song.getArtist().equalsIgnoreCase(artist)) {
                    result.add(song);
                }
            }
            return result;
        }
        
        public void shuffle() {
            Collections.shuffle(songs);
        }
        
        public boolean removeSong(Song song) {
            return songs.remove(song);
        }
        
        public boolean removeSong(String title, String artist) {
            Iterator<Song> iterator = songs.iterator();
            while (iterator.hasNext()) {
                Song song = iterator.next();
                if (song.getTitle().equalsIgnoreCase(title) && song.getArtist().equalsIgnoreCase(artist)) {
                    iterator.remove();
                    return true;
                }
            }
            return false;
        }
        
        public int getSongCount() {
            return songs.size();
        }
        
        public String getPlaylistInfo() {
            StringBuilder info = new StringBuilder();
            info.append("PLAYLIST: ").append(name).append("\n");
            info.append("📊 آمار: ").append(getSongCount()).append(" آهنگ | مدت کل: ").append(getFormattedTotalDuration()).append("\n");
            info.append("========================================\n");
            
            if (songs.isEmpty()) {
                info.append("پلی‌لیست خالی است!\n");
            } else {
                for (int i = 0; i < songs.size(); i++) {
                    info.append(i + 1).append(". ").append(songs.get(i).getSongInfo()).append("\n");
                }
            }
            return info.toString();
        }
        
        public String exportPlaylist() {
            StringBuilder export = new StringBuilder();
            export.append("Playlist: ").append(name).append("\n");
            export.append("Total Duration: ").append(getFormattedTotalDuration()).append("\n");
            export.append("Songs:\n");
            
            for (Song song : songs) {
                export.append("- ").append(song.getTitle()).append(" | ")
                      .append(song.getArtist()).append(" | ")
                      .append(song.getFormattedDuration()).append(" | ")
                      .append(song.getGenre()).append("\n");
            }
            return export.toString();
        }
        
        public String getName() { return name; }
        public ArrayList<Song> getSongs() { return new ArrayList<>(songs); }
        public void setName(String name) { this.name = name; }
    }
    
    public static void main(String[] args) {
        System.out.println("MUSIC PLAYLIST MANAGEMENT SYSTEM");
        System.out.println("===================================\n");
        
        // ایجاد آهنگ‌ها
        Song song1 = new Song("Bohemian Rhapsody", "Queen", 354, "Rock");
        Song song2 = new Song("Shape of You", "Ed Sheeran", 234, "Pop");
        Song song3 = new Song("Blinding Lights", "The Weeknd", 200, "Synthwave");
        Song song4 = new Song("Take Five", "Dave Brubeck", 324, "Jazz");
        Song song5 = new Song("Hotel California", "Eagles", 391, "Rock");
        Song song6 = new Song("Perfect", "Ed Sheeran", 263, "Pop");
        Song song7 = new Song("Yesterday", "The Beatles", 125, "Rock");
        
        // ایجاد پلی‌لیست
        Playlist myPlaylist = new Playlist("لیست پخش موردعلاقه");
        
        System.out.println("📝 افزودن آهنگ‌ها به پلی‌لیست:");
        System.out.println(myPlaylist.addSong(song1));
        System.out.println(myPlaylist.addSong(song2));
        System.out.println(myPlaylist.addSong(song3));
        System.out.println(myPlaylist.addSong(song4));
        System.out.println(myPlaylist.addSong(song5));
        System.out.println(myPlaylist.addSong(song6));
        System.out.println(myPlaylist.addSong(song7));
        
        System.out.println(" تست جلوگیری از آهنگ تکراری:");
        System.out.println(myPlaylist.addSong(song1));
        
        System.out.println(" نمایش پلی‌لیست اصلی:");
        System.out.println(myPlaylist.getPlaylistInfo());
        
        System.out.println(" جستجوی آهنگ‌های Ed Sheeran:");
        List<Song> edSongs = myPlaylist.findSongsByArtist("Ed Sheeran");
        for (Song song : edSongs) {
            System.out.println("   - " + song.getSongInfo());
        }
        
        System.out.println("\n به هم زدن پلی‌لیست:");
        myPlaylist.shuffle();
        System.out.println(myPlaylist.getPlaylistInfo());
        
        System.out.println(" صادر کردن پلی‌لیست:");
        System.out.println(myPlaylist.exportPlaylist());
        
        System.out.println("\n تست حذف آهنگ:");
        boolean removed = myPlaylist.removeSong("Take Five", "Dave Brubeck");
        System.out.println("آهنگ 'Take Five' حذف شد: " + removed);
        
        System.out.println("\n پلی‌لیست پس از حذف:");
        System.out.println(myPlaylist.getPlaylistInfo());
        
        System.out.println(" آمار نهایی پلی‌لیست:");
        System.out.println("تعداد آهنگ‌ها: " + myPlaylist.getSongCount());
        System.out.println("مدت کل: " + myPlaylist.getFormattedTotalDuration());
        
        System.out.println("\n تست پلی‌لیست خالی:");
        Playlist emptyPlaylist = new Playlist("پلی‌لیست خالی");
        System.out.println(emptyPlaylist.getPlaylistInfo());
        
        System.out.println(" اطلاعات نمونه آهنگ‌ها:");
        System.out.println(song1.getSongInfo());
        System.out.println(song2.getSongInfo());
        System.out.println(song3.getSongInfo());
        
        System.out.println("\n سیستم مدیریت پلی‌لیست موسیقی با موفقیت اجرا شد!");
    }
}