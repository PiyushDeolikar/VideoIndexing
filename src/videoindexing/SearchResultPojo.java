/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing;

/**
 *
 * @author Sony
 */
public class SearchResultPojo {
    private String videoName;
    private String wordCount;

    public SearchResultPojo() {
    }

    public SearchResultPojo(String videoName, String wordCount, String url) {
        this.videoName = videoName;
        this.wordCount = wordCount;
        this.url = url;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    private String url;
}