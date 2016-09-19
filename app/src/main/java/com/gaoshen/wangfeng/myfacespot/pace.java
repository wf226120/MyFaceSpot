package com.gaoshen.wangfeng.myfacespot;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class pace {


    /**
     * face : [{"position":{"mouth_right":{"y":47.560167,"x":58.193872},"mouth_left":{"y":45.060833,"x":30.546518},"center":{"y":34.75,"x":50.278552},"height":42.166667,"width":70.473538,"nose":{"y":38.612167,"x":51.253482},"eye_left":{"y":23.971333,"x":35.691922},"eye_right":{"y":31.545333,"x":74.959053}},"attribute":{"race":{"value":"Black","confidence":76.3744},"gender":{"value":"Female","confidence":99.9869},"smiling":{"value":56.4846},"age":{"value":2,"range":5}},"tag":"","face_id":"bfd02055ee80c22e867ef9c4b61acc6d"}]
     * session_id : 20f0ef581ac240c3beae16f3c092f1cf
     * img_height : 1296
     * img_width : 776
     * img_id : 0cdc3b6d6fbc2d4102152e032df9c0b2
     * url : null
     * response_code : 200
     */

    private String session_id;
    private int img_height;
    private int img_width;
    private String img_id;
    private Object url;
    private int response_code;
    /**
     * position : {"mouth_right":{"y":47.560167,"x":58.193872},"mouth_left":{"y":45.060833,"x":30.546518},"center":{"y":34.75,"x":50.278552},"height":42.166667,"width":70.473538,"nose":{"y":38.612167,"x":51.253482},"eye_left":{"y":23.971333,"x":35.691922},"eye_right":{"y":31.545333,"x":74.959053}}
     * attribute : {"race":{"value":"Black","confidence":76.3744},"gender":{"value":"Female","confidence":99.9869},"smiling":{"value":56.4846},"age":{"value":2,"range":5}}
     * tag :
     * face_id : bfd02055ee80c22e867ef9c4b61acc6d
     */

    private List<FaceBean> face;

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public int getImg_height() {
        return img_height;
    }

    public void setImg_height(int img_height) {
        this.img_height = img_height;
    }

    public int getImg_width() {
        return img_width;
    }

    public void setImg_width(int img_width) {
        this.img_width = img_width;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public List<FaceBean> getFace() {
        return face;
    }

    public void setFace(List<FaceBean> face) {
        this.face = face;
    }

    public static class FaceBean {
        /**
         * mouth_right : {"y":47.560167,"x":58.193872}
         * mouth_left : {"y":45.060833,"x":30.546518}
         * center : {"y":34.75,"x":50.278552}
         * height : 42.166667
         * width : 70.473538
         * nose : {"y":38.612167,"x":51.253482}
         * eye_left : {"y":23.971333,"x":35.691922}
         * eye_right : {"y":31.545333,"x":74.959053}
         */

        private PositionBean position;
        /**
         * race : {"value":"Black","confidence":76.3744}
         * gender : {"value":"Female","confidence":99.9869}
         * smiling : {"value":56.4846}
         * age : {"value":2,"range":5}
         */

        private AttributeBean attribute;
        private String tag;
        private String face_id;

        public PositionBean getPosition() {
            return position;
        }

        public void setPosition(PositionBean position) {
            this.position = position;
        }

        public AttributeBean getAttribute() {
            return attribute;
        }

        public void setAttribute(AttributeBean attribute) {
            this.attribute = attribute;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getFace_id() {
            return face_id;
        }

        public void setFace_id(String face_id) {
            this.face_id = face_id;
        }

        public static class PositionBean {
            /**
             * y : 47.560167
             * x : 58.193872
             */

            private MouthRightBean mouth_right;
            /**
             * y : 45.060833
             * x : 30.546518
             */

            private MouthLeftBean mouth_left;
            /**
             * y : 34.75
             * x : 50.278552
             */

            private CenterBean center;
            private double height;
            private double width;
            /**
             * y : 38.612167
             * x : 51.253482
             */

            private NoseBean nose;
            /**
             * y : 23.971333
             * x : 35.691922
             */

            private EyeLeftBean eye_left;
            /**
             * y : 31.545333
             * x : 74.959053
             */

            private EyeRightBean eye_right;

            public MouthRightBean getMouth_right() {
                return mouth_right;
            }

            public void setMouth_right(MouthRightBean mouth_right) {
                this.mouth_right = mouth_right;
            }

            public MouthLeftBean getMouth_left() {
                return mouth_left;
            }

            public void setMouth_left(MouthLeftBean mouth_left) {
                this.mouth_left = mouth_left;
            }

            public CenterBean getCenter() {
                return center;
            }

            public void setCenter(CenterBean center) {
                this.center = center;
            }

            public double getHeight() {
                return height;
            }

            public void setHeight(double height) {
                this.height = height;
            }

            public double getWidth() {
                return width;
            }

            public void setWidth(double width) {
                this.width = width;
            }

            public NoseBean getNose() {
                return nose;
            }

            public void setNose(NoseBean nose) {
                this.nose = nose;
            }

            public EyeLeftBean getEye_left() {
                return eye_left;
            }

            public void setEye_left(EyeLeftBean eye_left) {
                this.eye_left = eye_left;
            }

            public EyeRightBean getEye_right() {
                return eye_right;
            }

            public void setEye_right(EyeRightBean eye_right) {
                this.eye_right = eye_right;
            }

            public static class MouthRightBean {
                private double y;
                private double x;

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }
            }

            public static class MouthLeftBean {
                private double y;
                private double x;

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }
            }

            public static class CenterBean {
                private double y;
                private double x;

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }
            }

            public static class NoseBean {
                private double y;
                private double x;

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }
            }

            public static class EyeLeftBean {
                private double y;
                private double x;

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }
            }

            public static class EyeRightBean {
                private double y;
                private double x;

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }
            }
        }

        public static class AttributeBean {
            /**
             * value : Black
             * confidence : 76.3744
             */

            private RaceBean race;
            /**
             * value : Female
             * confidence : 99.9869
             */

            private GenderBean gender;
            /**
             * value : 56.4846
             */

            private SmilingBean smiling;
            /**
             * value : 2
             * range : 5
             */

            private AgeBean age;

            public RaceBean getRace() {
                return race;
            }

            public void setRace(RaceBean race) {
                this.race = race;
            }

            public GenderBean getGender() {
                return gender;
            }

            public void setGender(GenderBean gender) {
                this.gender = gender;
            }

            public SmilingBean getSmiling() {
                return smiling;
            }

            public void setSmiling(SmilingBean smiling) {
                this.smiling = smiling;
            }

            public AgeBean getAge() {
                return age;
            }

            public void setAge(AgeBean age) {
                this.age = age;
            }

            public static class RaceBean {
                private String value;
                private double confidence;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public double getConfidence() {
                    return confidence;
                }

                public void setConfidence(double confidence) {
                    this.confidence = confidence;
                }
            }

            public static class GenderBean {
                private String value;
                private double confidence;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public double getConfidence() {
                    return confidence;
                }

                public void setConfidence(double confidence) {
                    this.confidence = confidence;
                }
            }

            public static class SmilingBean {
                private double value;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }
            }

            public static class AgeBean {
                private int value;
                private int range;

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public int getRange() {
                    return range;
                }

                public void setRange(int range) {
                    this.range = range;
                }
            }
        }
    }
}
