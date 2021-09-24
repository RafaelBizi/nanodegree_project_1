$('document').ready(function() {

    $('.table .btn').on('click', function(event){

        event.preventDefault();

        var href= $(this).attr('href');

        $.get(href, function(note, status){
            $('#noteIdEdit').val(note.noteId);
            $('#noteTitleEdit').val(note.noteTitle);
            $('#noteDescriptionEdit').val(note.noteDescription);
        });

        $('#editNoteModal').modal();

    });

});
