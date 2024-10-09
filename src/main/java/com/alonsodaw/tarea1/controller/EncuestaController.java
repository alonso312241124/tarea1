package com.alonsodaw.tarea1.controller;

import com.alonsodaw.tarea1.entity.Encuesta;
import com.alonsodaw.tarea1.repository.EncuestaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class EncuestaController {

    private EncuestaRepository encuestaRepository;

    public EncuestaController(EncuestaRepository repository){
        this.encuestaRepository = repository;
    }

    @GetMapping("/encuestas/admin")
    public String findAll(@RequestParam(required = false) String filtroSatisfaccion, Model model) {
        List<Encuesta> encuestas = this.encuestaRepository.findAll();

        if (filtroSatisfaccion != null && !filtroSatisfaccion.isEmpty()) {
            encuestas = this.encuestaRepository.findByNivelSatisfaccion(filtroSatisfaccion);
        } else {
            encuestas = this.encuestaRepository.findAll();
        }

        // Variables para el promedio de edades
        int sumaEdades = 0;
        int totalEncuestas = encuestas.size();
        double promedioEdades = 0;

        // Variables para el desglose por porcentaje
        int muySatisfechoCont = 0;
        int satisfechoCont = 0;
        int neutralCont = 0;
        int insatisfechoCont = 0;
        int muyInsatisfechoCont = 0;

        for (Encuesta encuesta : encuestas) {
            sumaEdades += encuesta.getEdad();

            switch (encuesta.getNivelSatisfaccion()) {
                case "Muy Satisfecho":
                    muySatisfechoCont++;
                    break;
                case "Satisfecho":
                    satisfechoCont++;
                    break;
                case "Neutral":
                    neutralCont++;
                    break;
                case "Insatisfecho":
                    insatisfechoCont++;
                    break;
                case "Muy Insatisfecho":
                    muyInsatisfechoCont++;
                    break;
            }
        }

        double muySatisfechoPorcentaje = 0;
        double satisfechoPorcentaje = 0;
        double neutralPorcentaje = 0;
        double insatisfechoPorcentaje = 0;
        double muyInsatisfechoPorcentaje = 0;

        if (totalEncuestas > 0) {
            muySatisfechoPorcentaje = (muySatisfechoCont * 100.0) / totalEncuestas;
            satisfechoPorcentaje = (satisfechoCont * 100.0) / totalEncuestas;
            neutralPorcentaje = (neutralCont * 100.0) / totalEncuestas;
            insatisfechoPorcentaje = (insatisfechoCont * 100.0) / totalEncuestas;
            muyInsatisfechoPorcentaje = (muyInsatisfechoCont * 100.0) / totalEncuestas;
        }

        if (totalEncuestas > 0) {
            promedioEdades = (double) sumaEdades / totalEncuestas;
        } else {
            promedioEdades = 0;
        }

        model.addAttribute("encuestas", encuestas);
        model.addAttribute("promedioEdades", promedioEdades);
        model.addAttribute("muySatisfechoPorcentaje", muySatisfechoPorcentaje);
        model.addAttribute("satisfechoPorcentaje", satisfechoPorcentaje);
        model.addAttribute("neutralPorcentaje", neutralPorcentaje);
        model.addAttribute("insatisfechoPorcentaje", insatisfechoPorcentaje);
        model.addAttribute("muyInsatisfechoPorcentaje", muyInsatisfechoPorcentaje);
        model.addAttribute("filtroSatisfaccion", filtroSatisfaccion);

        return "encuesta-list";
    }

    @GetMapping("/encuesta")
    public String newEncuesta(Model model){
        model.addAttribute("encuesta", new Encuesta());
        return "encuesta-new";
    }

    @PostMapping("/encuesta")
    public String newEncuestaPOST(@Valid Encuesta encuesta, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "encuesta-new";
        }
        encuestaRepository.save(encuesta);

        return "redirect:/encuesta/enviada";
    }

    @GetMapping("/encuesta/enviada")
    public String agradecimientoEncuesta(){
        return "encuesta-enviada";
    }

    @GetMapping("/encuesta/view/{id}")
    public String view(@PathVariable Long id, Model model){
        Optional encuesta = encuestaRepository.findById(id);

        if(encuesta.isPresent()){
            model.addAttribute("encuesta", encuesta.get());
            return "encuesta-view";
        }else{
            return "redirect:/encuestas/admin";
        }
    }

    @GetMapping("/encuesta/del/{id}")
    public String delete(@PathVariable Long id){
        encuestaRepository.deleteById(id);
        return "redirect:/encuestas/admin";
    }

    @GetMapping("/encuesta/edit/{id}")
    public String editEncuesta(@PathVariable Long id, Model model){
        Optional<Encuesta> encuesta = encuestaRepository.findById(id);
        if (encuesta.isPresent()){
            model.addAttribute("encuesta", encuesta.get());
            return "encuesta-edit";
        }

        return "redirect:/encuestas/admin";
    }

    @PostMapping("/encuesta/edit/{id}")
    public String encuestaUpdate(@PathVariable Long id, Encuesta encuesta){
        encuesta.setId(id);
        encuestaRepository.save(encuesta);

        return "redirect:/encuestas/admin";
    }


}
