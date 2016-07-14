package com.example.dllo.chanyouji.youji_details;

import java.util.List;

/**
 * Created by dllo on 16/6/17.
 */
public class YouJidetailsBean {
    private int id;
    private String name;
    private int photos_count;
    private String start_date;
    private String end_date;
    private int level;
    private boolean privacy;
    private int front_cover_photo_id;
    private int views_count;
    private int comments_count;
    private int likes_count;
    private int favorites_count;
    private String state;
    private String source;
    private int serial_id;
    private int serial_position;
    private int serial_next_id;
    private int updated_at;

    private List<TripDaysBean> trip_days;

    private UserBean user;
    private Object upload_token;
    private boolean current_user_favorite;
    private Object password;
    private String front_cover_photo_url;

    private List<NotesLikesCommentsBean> notes_likes_comments;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public int getFront_cover_photo_id() {
        return front_cover_photo_id;
    }

    public void setFront_cover_photo_id(int front_cover_photo_id) {
        this.front_cover_photo_id = front_cover_photo_id;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getFavorites_count() {
        return favorites_count;
    }

    public void setFavorites_count(int favorites_count) {
        this.favorites_count = favorites_count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(int serial_id) {
        this.serial_id = serial_id;
    }

    public int getSerial_position() {
        return serial_position;
    }

    public void setSerial_position(int serial_position) {
        this.serial_position = serial_position;
    }

    public int getSerial_next_id() {
        return serial_next_id;
    }

    public void setSerial_next_id(int serial_next_id) {
        this.serial_next_id = serial_next_id;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Object getUpload_token() {
        return upload_token;
    }

    public void setUpload_token(Object upload_token) {
        this.upload_token = upload_token;
    }

    public boolean isCurrent_user_favorite() {
        return current_user_favorite;
    }

    public void setCurrent_user_favorite(boolean current_user_favorite) {
        this.current_user_favorite = current_user_favorite;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getFront_cover_photo_url() {
        return front_cover_photo_url;
    }

    public void setFront_cover_photo_url(String front_cover_photo_url) {
        this.front_cover_photo_url = front_cover_photo_url;
    }

    public List<TripDaysBean> getTrip_days() {
        return trip_days;
    }

    public void setTrip_days(List<TripDaysBean> trip_days) {
        this.trip_days = trip_days;
    }

    public List<NotesLikesCommentsBean> getNotes_likes_comments() {
        return notes_likes_comments;
    }

    public void setNotes_likes_comments(List<NotesLikesCommentsBean> notes_likes_comments) {
        this.notes_likes_comments = notes_likes_comments;
    }

    //内部类UserBean
    public static class UserBean {
        private int id;
        private String name;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    //内部类TripDaysBean,返回一个集合
    public static class TripDaysBean {

        private int id;
        private String trip_date;
        private int day;
        private int updated_at;
        private DestinationBean destination;
        private List<NodesBean> nodes;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTrip_date() {
            return trip_date;
        }

        public void setTrip_date(String trip_date) {
            this.trip_date = trip_date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
        }

        public DestinationBean getDestination() {
            return destination;
        }

        public void setDestination(DestinationBean destination) {
            this.destination = destination;
        }

        public List<NodesBean> getNodes() {
            return nodes;
        }

        public void setNodes(List<NodesBean> nodes) {
            this.nodes = nodes;
        }

        public static class DestinationBean {
            private int id;
            private String name_zh_cn;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName_zh_cn() {
                return name_zh_cn;
            }

            public void setName_zh_cn(String name_zh_cn) {
                this.name_zh_cn = name_zh_cn;
            }
        }

        public static class NodesBean {
            private int id;
            private int row_order;
            private int score;
            private String comment;
            private String tips;
            private MemoBean memo;
            private int entry_id;
            private float lat;
            private float lng;
            private String entry_type;
            private boolean user_entry;
            private String entry_name;
            private String attraction_type;
            private int updated_at;

            private List<NotesBean> notes;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRow_order() {
                return row_order;
            }

            public void setRow_order(int row_order) {
                this.row_order = row_order;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }

            public MemoBean getMemo() {
                return memo;
            }

            public void setMemo(MemoBean memo) {
                this.memo = memo;
            }

            public int getEntry_id() {
                return entry_id;
            }

            public void setEntry_id(int entry_id) {
                this.entry_id = entry_id;
            }

            public float getLat() {
                return lat;
            }

            public void setLat(float lat) {
                this.lat = lat;
            }

            public float getLng() {
                return lng;
            }

            public void setLng(float lng) {
                this.lng = lng;
            }

            public String getEntry_type() {
                return entry_type;
            }

            public void setEntry_type(String entry_type) {
                this.entry_type = entry_type;
            }

            public boolean isUser_entry() {
                return user_entry;
            }

            public void setUser_entry(boolean user_entry) {
                this.user_entry = user_entry;
            }

            public String getEntry_name() {
                return entry_name;
            }

            public void setEntry_name(String entry_name) {
                this.entry_name = entry_name;
            }

            public String getAttraction_type() {
                return attraction_type;
            }

            public void setAttraction_type(String attraction_type) {
                this.attraction_type = attraction_type;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public List<NotesBean> getNotes() {
                return notes;
            }

            public void setNotes(List<NotesBean> notes) {
                this.notes = notes;
            }

            public static class MemoBean {
                private String time;
                private String time_unit;
                private String price_amount;
                private String price_currency;

                public String getPrice_currency() {
                    return price_currency;
                }

                public void setPrice_currency(String price_currency) {
                    this.price_currency = price_currency;
                }

                public String getTime_unit() {
                    return time_unit;
                }

                public void setTime_unit(String time_unit) {
                    this.time_unit = time_unit;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getPrice_amount() {
                    return price_amount;
                }

                public void setPrice_amount(String price_amount) {
                    this.price_amount = price_amount;
                }
            }

            public static class NotesBean {
                private int id;
                private int row_order;
                private String layout;
                private int col;
                private String description;
                private int updated_at;
                private PhotoBean photo;

                public static class PhotoBean {
                    private int id;
                    private int image_width;
                    private int image_height;
                    private int image_file_size;
                    private float exif_lat;
                    private float exif_lng;
                    private int exif_date_time_original;
                    private String url;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getImage_width() {
                        return image_width;
                    }

                    public void setImage_width(int image_width) {
                        this.image_width = image_width;
                    }

                    public int getImage_height() {
                        return image_height;
                    }

                    public void setImage_height(int image_height) {
                        this.image_height = image_height;
                    }

                    public int getImage_file_size() {
                        return image_file_size;
                    }

                    public void setImage_file_size(int image_file_size) {
                        this.image_file_size = image_file_size;
                    }

                    public float getExif_lat() {
                        return exif_lat;
                    }

                    public void setExif_lat(float exif_lat) {
                        this.exif_lat = exif_lat;
                    }

                    public float getExif_lng() {
                        return exif_lng;
                    }

                    public void setExif_lng(float exif_lng) {
                        this.exif_lng = exif_lng;
                    }

                    public int getExif_date_time_original() {
                        return exif_date_time_original;
                    }

                    public void setExif_date_time_original(int exif_date_time_original) {
                        this.exif_date_time_original = exif_date_time_original;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }

                public PhotoBean getPhoto() {
                    return photo;
                }

                public void setPhotos(PhotoBean photo) {
                    this.photo = photo;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getRow_order() {
                    return row_order;
                }

                public void setRow_order(int row_order) {
                    this.row_order = row_order;
                }

                public String getLayout() {
                    return layout;
                }

                public void setLayout(String layout) {
                    this.layout = layout;
                }

                public int getCol() {
                    return col;
                }

                public void setCol(int col) {
                    this.col = col;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public int getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(int updated_at) {
                    this.updated_at = updated_at;
                }
            }
        }
    }

    public static class NotesLikesCommentsBean {
        private int id;
        private int comments_count;
        private int likes_count;
        private boolean current_user_like;
        private boolean current_user_comment;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public int getLikes_count() {
            return likes_count;
        }

        public void setLikes_count(int likes_count) {
            this.likes_count = likes_count;
        }

        public boolean isCurrent_user_like() {
            return current_user_like;
        }

        public void setCurrent_user_like(boolean current_user_like) {
            this.current_user_like = current_user_like;
        }

        public boolean isCurrent_user_comment() {
            return current_user_comment;
        }

        public void setCurrent_user_comment(boolean current_user_comment) {
            this.current_user_comment = current_user_comment;
        }
    }



}
