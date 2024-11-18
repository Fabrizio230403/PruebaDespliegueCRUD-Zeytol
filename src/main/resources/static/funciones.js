function eliminar(id) {
    Swal.fire({
        title: '¿Estás seguro de eliminar este producto?',
        text: "Una vez eliminado, no podrás recuperar la información de este producto.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminar!',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/eliminar/" + id,
                success: function (res) {
                    console.log(res);
                 
                    Swal.fire({
                        title: '¡Eliminado!',
                        text: 'El producto ha sido eliminado exitosamente.',
                        icon: 'success'
                    }).then((ok) => {
                        if (ok) {
                            location.href = "/listar"; // Redirigir a la lista
                        }
                    });
                },
                error: function (err) {
                    Swal.fire({
                        title: 'Error!',
                        text: 'Hubo un problema al eliminar el producto.',
                        icon: 'error'
                    });
                }
            });
        } else {
            Swal.fire('El producto no ha sido eliminado.');
        }
    });
	
	
}

//Function para previsualizar imágenes

function previewImage() {
    const fileInput = document.getElementById("foto");
    const preview = document.getElementById("fotoPreview");
    const file = fileInput.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
        }
        reader.readAsDataURL(file);
    } else {
        preview.style.display = 'none';
    }
}
