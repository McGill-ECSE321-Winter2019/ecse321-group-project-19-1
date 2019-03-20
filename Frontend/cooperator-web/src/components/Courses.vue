<template>
<div id="id">

    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">Course Ranking</h1>
                <div id="table">
                    <table>
                        <tr>
                            <th>Course Name</th>
                            <th>Number of Coops Used</th>
                        </tr>
                        <tr v-for="course in courses">
                            <td>{{ course.courseName }}</td>
                            <td>{{coopLength(course)}}</td>
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
