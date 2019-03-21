<template>
<div id="id">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">Course Ranking</h1>
                <div id="table">
                    <table class="info-table" style="width: 100%; height: 100%;">
                        <tr>
                            <th style="padding:10px">Course Name</th>
                            <th style="padding:10px">Number of Coops Used</th>
                        </tr>
                        <tr v-for="course in courses">
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
