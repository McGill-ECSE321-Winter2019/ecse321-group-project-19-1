<template>
<div id="id">
        <div class="site-wrap">
            <div class="site-section" style="padding-top:10%">
                <div class="container">
                    <h1 class="page-title" align="center"> {{student.firstName}} {{student.lastName}}</h1>
                    <b-table ref="table" class="info-table" striped hover :items= "documents" :fields= "fields"/>

                     </div>
                </div>
            </div>
        </div>
</template>

<script src="./javascript/PmSingleStudent.js">
</script>