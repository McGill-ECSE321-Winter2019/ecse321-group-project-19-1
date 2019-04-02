<template>
<div id="id">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="text-align:center; ">
                <h1 class="page-title">Create Course</h1>
                <input
                        class="text"
                        type="text"
                        style="margin: 15px; border-radius:2%;"
                        placeholder="Course Name"
                        v-model="newCourse"
                        />
                    <br>
                <input @click="createCourse(newCourse)"
                        type="submit"
                        value="Create"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                <div class="container" align="center">
                    <span v-if="errorNewCourse" style="color:red">Error: {{errorNewCourse}} </span>
                    <br>
                </div>
                </div>
                <br>
                <hr style="border: 1px solid white; width:60%; opacity:0.4;" />
                <div class="container" style="text-align:center">
                    <h1 class="page-title">Add Course to Coop</h1>
                    <input
                        class="text"
                        type="text"
                        style="border-radius:2%;"
                        placeholder="CoopID"
                        v-model="coopId"
                        />
                        <br>
                    <input
                        class="text"
                        type="text"
                        style="margin: 15px; border-radius:2%;"
                        placeholder="CourseID"
                        v-model="courseId"
                        />
                        <br>
                    <input @click="rateCourse(coopId, courseId)"
                        type="submit"
                        value="Rate Course"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                    <div class="container" align="center">
                        <span v-if="errorCourse" style="color:red">Error: {{errorCourse}} </span>
                        <br>
                    </div>
                    </div>
                    
                    <hr style="border: 1px solid white; width:60%; opacity:0.4;" />

                    <div class="container" style="align:center">
                        <h1 class="page-title">Course Ranking</h1>
                        <div id="table">
                            <table class="info-table" style="width: 100%; height: 100%;">
                                <tr>
                                    <th style="padding:10px">Course ID</th>
                                    <th style="padding:10px">Course Name</th>
                                    <th style="padding:10px">Number of Coops Used</th>
                                </tr>
                                <tr v-for="course in courses">
                                    <td style="padding:10px">{{ course.courseId }}</td>
                                    <td style="padding:10px">{{ course.courseName }}</td>
                                    <td style="padding:10px">{{coopLength(course)}}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</template>

<script src="./javascript/course.js">
</script>
