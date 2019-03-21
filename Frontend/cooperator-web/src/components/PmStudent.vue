<template>
<div id="id">

        <div class="site-wrap">
            <div class="site-section" style="padding-top:10%">
                <div class="container" style="align:center">
                    <h1 class="page-title">Program Manager Students</h1>
                    <div id="table" >
                            <b-table  ref="table" class="info-table" selectable select-mode="single" striped hover :items= "students" :fields= "fields" @row-selected="studentSelection"/>
                     </div>
                </div>
            </div>
        </div>
</div>
</template>

<script src="./javascript/PmStudent.js">
</script>
