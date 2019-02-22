package com.stayli.app.model.domain;

import java.util.List;

/**
 * Created by  yahuigao
 * Date: 2019/1/23
 * Time: 15:22
 * Description:
 */
public class FaceInfo {


    /**
     * ret : 0
     * msg : ok
     * data : {"image_width":131,"image_height":131,"face_list":[{"face_id":"2946639217946546793","x":11,"y":15,"width":111,"height":111,"gender":0,"age":17,"expression":34,"beauty":100,"glass":0,"pitch":16,"yaw":-1,"roll":2,"face_shape":{"face_profile":[{"x":12,"y":45},{"x":13,"y":55},{"x":15,"y":66},{"x":18,"y":77},{"x":21,"y":87},{"x":26,"y":96},{"x":32,"y":105},{"x":39,"y":114},{"x":47,"y":122},{"x":56,"y":128},{"x":66,"y":130},{"x":77,"y":128},{"x":86,"y":121},{"x":94,"y":113},{"x":100,"y":104},{"x":106,"y":95},{"x":110,"y":84},{"x":112,"y":73},{"x":114,"y":62},{"x":115,"y":51},{"x":114,"y":41}],"left_eye":[{"x":25,"y":45},{"x":29,"y":49},{"x":35,"y":50},{"x":41,"y":49},{"x":47,"y":46},{"x":43,"y":41},{"x":36,"y":39},{"x":30,"y":40}],"right_eye":[{"x":101,"y":40},{"x":96,"y":45},{"x":90,"y":47},{"x":84,"y":47},{"x":77,"y":46},{"x":81,"y":40},{"x":87,"y":36},{"x":95,"y":36}],"left_eyebrow":[{"x":14,"y":30},{"x":22,"y":28},{"x":30,"y":27},{"x":39,"y":27},{"x":47,"y":27},{"x":40,"y":22},{"x":30,"y":21},{"x":21,"y":23}],"right_eyebrow":[{"x":110,"y":24},{"x":101,"y":23},{"x":92,"y":23},{"x":83,"y":25},{"x":74,"y":26},{"x":82,"y":19},{"x":91,"y":17},{"x":102,"y":18}],"mouth":[{"x":47,"y":96},{"x":51,"y":102},{"x":57,"y":107},{"x":64,"y":108},{"x":72,"y":107},{"x":78,"y":103},{"x":83,"y":97},{"x":76,"y":95},{"x":70,"y":93},{"x":65,"y":94},{"x":60,"y":93},{"x":53,"y":94},{"x":52,"y":99},{"x":58,"y":101},{"x":64,"y":102},{"x":71,"y":101},{"x":77,"y":100},{"x":77,"y":97},{"x":71,"y":97},{"x":65,"y":97},{"x":59,"y":97},{"x":53,"y":96}],"nose":[{"x":63,"y":77},{"x":61,"y":46},{"x":59,"y":55},{"x":56,"y":63},{"x":53,"y":71},{"x":50,"y":79},{"x":57,"y":83},{"x":64,"y":85},{"x":71,"y":83},{"x":79,"y":78},{"x":75,"y":70},{"x":70,"y":62},{"x":66,"y":54}]}}]}
     */

    public int ret;
    public String msg;
    /**
     * image_width : 131
     * image_height : 131
     * face_list : [{"face_id":"2946639217946546793","x":11,"y":15,"width":111,"height":111,"gender":0,"age":17,"expression":34,"beauty":100,"glass":0,"pitch":16,"yaw":-1,"roll":2,"face_shape":{"face_profile":[{"x":12,"y":45},{"x":13,"y":55},{"x":15,"y":66},{"x":18,"y":77},{"x":21,"y":87},{"x":26,"y":96},{"x":32,"y":105},{"x":39,"y":114},{"x":47,"y":122},{"x":56,"y":128},{"x":66,"y":130},{"x":77,"y":128},{"x":86,"y":121},{"x":94,"y":113},{"x":100,"y":104},{"x":106,"y":95},{"x":110,"y":84},{"x":112,"y":73},{"x":114,"y":62},{"x":115,"y":51},{"x":114,"y":41}],"left_eye":[{"x":25,"y":45},{"x":29,"y":49},{"x":35,"y":50},{"x":41,"y":49},{"x":47,"y":46},{"x":43,"y":41},{"x":36,"y":39},{"x":30,"y":40}],"right_eye":[{"x":101,"y":40},{"x":96,"y":45},{"x":90,"y":47},{"x":84,"y":47},{"x":77,"y":46},{"x":81,"y":40},{"x":87,"y":36},{"x":95,"y":36}],"left_eyebrow":[{"x":14,"y":30},{"x":22,"y":28},{"x":30,"y":27},{"x":39,"y":27},{"x":47,"y":27},{"x":40,"y":22},{"x":30,"y":21},{"x":21,"y":23}],"right_eyebrow":[{"x":110,"y":24},{"x":101,"y":23},{"x":92,"y":23},{"x":83,"y":25},{"x":74,"y":26},{"x":82,"y":19},{"x":91,"y":17},{"x":102,"y":18}],"mouth":[{"x":47,"y":96},{"x":51,"y":102},{"x":57,"y":107},{"x":64,"y":108},{"x":72,"y":107},{"x":78,"y":103},{"x":83,"y":97},{"x":76,"y":95},{"x":70,"y":93},{"x":65,"y":94},{"x":60,"y":93},{"x":53,"y":94},{"x":52,"y":99},{"x":58,"y":101},{"x":64,"y":102},{"x":71,"y":101},{"x":77,"y":100},{"x":77,"y":97},{"x":71,"y":97},{"x":65,"y":97},{"x":59,"y":97},{"x":53,"y":96}],"nose":[{"x":63,"y":77},{"x":61,"y":46},{"x":59,"y":55},{"x":56,"y":63},{"x":53,"y":71},{"x":50,"y":79},{"x":57,"y":83},{"x":64,"y":85},{"x":71,"y":83},{"x":79,"y":78},{"x":75,"y":70},{"x":70,"y":62},{"x":66,"y":54}]}}]
     */

    public DataBean data;

    public static class DataBean {
        public int image_width;
        public int image_height;
        /**
         * face_id : 2946639217946546793
         * x : 11
         * y : 15
         * width : 111
         * height : 111
         * gender : 0
         * age : 17
         * expression : 34
         * beauty : 100
         * glass : 0
         * pitch : 16
         * yaw : -1
         * roll : 2
         * face_shape : {"face_profile":[{"x":12,"y":45},{"x":13,"y":55},{"x":15,"y":66},{"x":18,"y":77},{"x":21,"y":87},{"x":26,"y":96},{"x":32,"y":105},{"x":39,"y":114},{"x":47,"y":122},{"x":56,"y":128},{"x":66,"y":130},{"x":77,"y":128},{"x":86,"y":121},{"x":94,"y":113},{"x":100,"y":104},{"x":106,"y":95},{"x":110,"y":84},{"x":112,"y":73},{"x":114,"y":62},{"x":115,"y":51},{"x":114,"y":41}],"left_eye":[{"x":25,"y":45},{"x":29,"y":49},{"x":35,"y":50},{"x":41,"y":49},{"x":47,"y":46},{"x":43,"y":41},{"x":36,"y":39},{"x":30,"y":40}],"right_eye":[{"x":101,"y":40},{"x":96,"y":45},{"x":90,"y":47},{"x":84,"y":47},{"x":77,"y":46},{"x":81,"y":40},{"x":87,"y":36},{"x":95,"y":36}],"left_eyebrow":[{"x":14,"y":30},{"x":22,"y":28},{"x":30,"y":27},{"x":39,"y":27},{"x":47,"y":27},{"x":40,"y":22},{"x":30,"y":21},{"x":21,"y":23}],"right_eyebrow":[{"x":110,"y":24},{"x":101,"y":23},{"x":92,"y":23},{"x":83,"y":25},{"x":74,"y":26},{"x":82,"y":19},{"x":91,"y":17},{"x":102,"y":18}],"mouth":[{"x":47,"y":96},{"x":51,"y":102},{"x":57,"y":107},{"x":64,"y":108},{"x":72,"y":107},{"x":78,"y":103},{"x":83,"y":97},{"x":76,"y":95},{"x":70,"y":93},{"x":65,"y":94},{"x":60,"y":93},{"x":53,"y":94},{"x":52,"y":99},{"x":58,"y":101},{"x":64,"y":102},{"x":71,"y":101},{"x":77,"y":100},{"x":77,"y":97},{"x":71,"y":97},{"x":65,"y":97},{"x":59,"y":97},{"x":53,"y":96}],"nose":[{"x":63,"y":77},{"x":61,"y":46},{"x":59,"y":55},{"x":56,"y":63},{"x":53,"y":71},{"x":50,"y":79},{"x":57,"y":83},{"x":64,"y":85},{"x":71,"y":83},{"x":79,"y":78},{"x":75,"y":70},{"x":70,"y":62},{"x":66,"y":54}]}
         */

        public List<FaceListBean> face_list;

        public static class FaceListBean {
            public String face_id;
            public int x;
            public int y;
            public int width;
            public int height;
            public int gender;
            public int age;
            public int expression;
            public int beauty;
            public int glass;
            public int pitch;
            public int yaw;
            public int roll;
            public FaceShapeBean face_shape;

            public static class FaceShapeBean {
                /**
                 * x : 12
                 * y : 45
                 */

                public List<FaceProfileBean> face_profile;
                /**
                 * x : 25
                 * y : 45
                 */

                public List<LeftEyeBean> left_eye;
                /**
                 * x : 101
                 * y : 40
                 */

                public List<RightEyeBean> right_eye;
                /**
                 * x : 14
                 * y : 30
                 */

                public List<LeftEyebrowBean> left_eyebrow;
                /**
                 * x : 110
                 * y : 24
                 */

                public List<RightEyebrowBean> right_eyebrow;
                /**
                 * x : 47
                 * y : 96
                 */

                public List<MouthBean> mouth;
                /**
                 * x : 63
                 * y : 77
                 */

                public List<NoseBean> nose;

                public static class FaceProfileBean {
                    public int x;
                    public int y;
                }

                public static class LeftEyeBean {
                    public int x;
                    public int y;
                }

                public static class RightEyeBean {
                    public int x;
                    public int y;
                }

                public static class LeftEyebrowBean {
                    public int x;
                    public int y;
                }

                public static class RightEyebrowBean {
                    public int x;
                    public int y;
                }

                public static class MouthBean {
                    public int x;
                    public int y;
                }

                public static class NoseBean {
                    public int x;
                    public int y;
                }
            }
        }
    }
}
