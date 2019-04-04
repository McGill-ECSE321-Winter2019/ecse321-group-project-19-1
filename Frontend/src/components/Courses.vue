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
                <input @click="createCourse(newCourse)"
                        type="submit"
                        value="Create"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                <div class="container" align="center">
                    <span v-if="errorNewCourse" style="color:red">Error: {{errorNewCourse}} </span>
                    <br>
                </div>
                        <h1 class="page-title">Add Course to Coop</h1>
                        <input
                        class="text"
                        type="text"
                        style="margin: 15px; border-radius:2%;"
                        placeholder="CoopID"
                        v-model="coopId"
                        />
                        <input
                        class="text"
                        type="text"
                        style="margin: 15px; border-radius:2%;"
                        placeholder="CourseID"
                        v-model="courseId"
                        />
                        <input @click="rateCourse(coopId, courseId)"
                        type="submit"
                        value="Rate Course"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                        <div class="container" align="center">
                            <span v-if="errorCourse" style="color:red">Error: {{errorCourse}} </span>
                            <br>
                    </div>
                        <div id="table">
                            <b-table ref="table" class="info-table" striped :items="courses" :fields="fields" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
</template>

<script src="./javascript/course.js">
</script>
