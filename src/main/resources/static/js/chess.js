var Chess = Chess || {};

Chess.initialize = function() {
	// Remove eventos antigos
    $('.board tbody tr td').off('click');
    $('.board tbody tr td img').off('click');
    
	$('.piece').on('click', function(e) {
		Chess.clickPiece($(this));
	});
	
	$('.possible-movement').on('click', function(e) {
		Chess.movePiece($(this));
	});
};

Chess.clickPiece = function(element) {
	Chess.clearPossibleMoves();
	let boardSquare = element.closest('td');
	if(boardSquare.hasClass('possible-movement')){
		return;
	}
	let position = boardSquare.attr('data-position');
	let gameId = $('#gameId').val();
	let sucessCallback = function(response) {
		if(response.length > 0){
			response.forEach(function (item){
				$('td[data-position="' + item + '"]').addClass('possible-movement');
			});
		}
		Chess.initialize();
	};

	let errorCallback = function(response) {
		alert(response);
	};

	$.ajax({
		url: '/possible-play/' + gameId + '/' + position,
		method: 'GET', 
		success: sucessCallback,
		error: errorCallback
	});
};

Chess.movePiece = function(element){
	let position = element.attr('data-position');
	let gameId = $('#gameId').val();
	let sucessCallback = function(response) {
		$('#div-board').html(response);
		Chess.initialize();
	};

	let errorCallback = function(response) {
		alert(response);
	};
	
	$.ajax({
		url: '/move/' + gameId + '/' + position,
		method: 'GET', 
		success: sucessCallback,
		error: errorCallback
	});
};

Chess.clearPossibleMoves = function(){
	$('.board tbody tr td').removeClass('possible-movement');
};

$(function() {
	Chess.initialize();
});