package com.chat.types;

import com.chat.tools.Tools;
import org.javalite.activejdbc.Model;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by tyler on 6/19/16.
 */
public class DiscussionObj implements JSONWriter {
    private Long id, userId;
    private String userName, title, link, text;
    private Boolean private_, deleted;
    private Integer avgRank, userRank, numberOfVotes;
    private List<TagObj> tags;
    private List<UserObj> privateUsers, blockedUsers;
    private Timestamp created, modified;

    public DiscussionObj() {
    }

    public DiscussionObj(Long id,
                         Long userId,
                         String userName,
                         String title,
                         String link,
                         String text,
                         Boolean private_,
                         Integer avgRank,
                         Integer userRank,
                         Integer numberOfVotes,
                         String tagIds,
                         String tagNames,
                         String privateUserIds,
                         String privateUserNames,
                         String blockedUserIds,
                         String blockedUserNames,
                         Boolean deleted,
                         Timestamp created,
                         Timestamp modified) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.link = link;
        this.text = text;
        this.private_ = private_;
        this.avgRank = avgRank;
        this.userRank = userRank;
        this.numberOfVotes = numberOfVotes;
        this.tags = (!tagIds.contains("{NULL")) ? setTags(tagIds, tagNames) : null;
        this.privateUsers = setMultiUserType(privateUserIds, privateUserNames, true);
        this.blockedUsers = setMultiUserType(blockedUserIds, blockedUserNames, false);
        this.deleted = deleted;
        this.created = created;
        this.modified = modified;


    }

    public void checkPrivate(UserObj userObj) {
        if (getPrivate_().equals(true)) {
            if (!getPrivateUsers().contains(userObj)) {
                throw new NoSuchElementException("Private discussion, not allowed to view");
            }
        }
    }

    public void checkBlocked(UserObj userObj) {
        System.out.println(Arrays.toString(getBlockedUsers().toArray()));
        if (getBlockedUsers().contains(userObj)) {
            throw new NoSuchElementException("You have been blocked from this discussion");
        }
    }

    public static DiscussionObj create(Model d, Integer vote) {
        return new DiscussionObj(d.getLongId(),
                d.getLong("user_id"),
                d.getString("user_name"),
                d.getString("title"),
                d.getString("link"),
                d.getString("text_"),
                d.getBoolean("private"),
                d.getInteger("avg_rank"),
                vote,
                d.getInteger("number_of_votes"),
                d.getString("tag_ids"),
                d.getString("tag_names"),
                d.getString("private_user_ids"),
                d.getString("private_user_names"),
                d.getString("blocked_user_ids"),
                d.getString("blocked_user_names"),
                d.getBoolean("deleted"),
                d.getTimestamp("created"),
                d.getTimestamp("modified"));
    }

    public static DiscussionObj fromJson(String dataStr) {

        try {
            return Tools.JACKSON.readValue(dataStr, DiscussionObj.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<TagObj> setTags(String tagIds, String tagNames) {
        List<TagObj> tags = new ArrayList<>();
        String[] ids = Tools.pgArrayAggToArray(tagIds);
        String[] names = Tools.pgArrayAggToArray(tagNames);

        for (int i = 0; i < ids.length; i++) {
            tags.add(TagObj.create(Long.valueOf(ids[i]), names[i]));
        }

        List<TagObj> dedupeTagObjs = new ArrayList<>(new LinkedHashSet<>(tags));

        return dedupeTagObjs;
    }

    public List<UserObj> setMultiUserType(String multiUserIds, String multiUserNames, Boolean addCreated) {
        List<UserObj> users = new ArrayList<>();

        // Add the creating user
        if (addCreated) {
            UserObj creator = UserObj.create(getUserId(), getUserName());
            users.add(creator);
        }

        if (!multiUserIds.contains("{NULL")) {
            String[] ids = Tools.pgArrayAggToArray(multiUserIds);
            String[] names = Tools.pgArrayAggToArray(multiUserNames);
            for (int i = 0; i < ids.length; i++) {
                users.add(UserObj.create(Long.valueOf(ids[i]), names[i]));
            }
        }

        List<UserObj> dedupeUserObjs = new ArrayList<>(new LinkedHashSet<>(users));

        return dedupeUserObjs;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public Boolean getPrivate_() {
        return private_;
    }

    public Integer getAvgRank() {
        return avgRank;
    }

    public Integer getUserRank() {
        return userRank;
    }

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public List<TagObj> getTags() {
        return tags;
    }

    public List<UserObj> getPrivateUsers() {
        return privateUsers;
    }

    public List<UserObj> getBlockedUsers() {
        return blockedUsers;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
