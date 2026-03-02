package michael.oliveira.chess.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import michael.oliveira.chess.core.PieceDTO;
import michael.oliveira.chess.core.service.BoardService;
import michael.oliveira.chess.enums.ColunaEnum;
import michael.oliveira.chess.enums.LinhaEnum;
import michael.oliveira.chess.enums.TeamEnum;

@Controller("/")
public class InicioController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping
	public ModelAndView inicio(HttpServletRequest request) {
		return carregaJogo(request, boardService.createStartingBoard());
	}	
	
	@GetMapping("/{gameId}")
	public ModelAndView carregaJogo(HttpServletRequest request, @PathVariable("gameId")Integer gameId) {
		ModelAndView mv = new ModelAndView("index");
		carregaBases(mv, request);
		mv.addObject("board", boardService.getCurrentBoard(gameId));
		mv.addObject("gameId", gameId);
		return mv;
	}
	
	@GetMapping("/possible-play/{gameId}/{position}")
	public ResponseEntity<List<String>> getPossiblePlayByPosition(HttpServletRequest request, @PathVariable("gameId") Integer gameId, @PathVariable("position")String position) {
		HttpSession session = request.getSession();
		
		Map<String, PieceDTO> board = boardService.getCurrentBoard(gameId);
		TeamEnum currentPlayer = boardService.getCurrentPlayer(gameId);
		
		PieceDTO currentPiece = board.get(position);
		session.setAttribute("currentPiece", currentPiece);
		
		List<String> possiblePlays = boardService.getPossibleMovesByPosition(position, board, currentPlayer);
		return ResponseEntity.ok(possiblePlays);
	}
	
	@GetMapping("/move/{gameId}/{position}")
	public ModelAndView move(HttpServletRequest request,@PathVariable("gameId")Integer gameId, @PathVariable("position")String position) {
		ModelAndView mv = new ModelAndView("fragments/board-fragment :: board-fragment");
		
		HttpSession session = request.getSession();
		PieceDTO currentPiece = (PieceDTO)session.getAttribute("currentPiece");
		boardService.move(gameId, currentPiece, position);
		
		mv.addObject("linhas", LinhaEnum.values());
		mv.addObject("colunas", ColunaEnum.values());
		mv.addObject("board", boardService.getCurrentBoard(gameId));
		mv.addObject("gameId", gameId);
		
		return mv;
		
	}
	private void carregaBases(ModelAndView mv, HttpServletRequest request) {
		mv.addObject("linhas", LinhaEnum.values());
		mv.addObject("colunas", ColunaEnum.values());
	}
}
