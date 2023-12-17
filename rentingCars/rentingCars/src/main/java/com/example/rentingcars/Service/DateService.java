package com.example.rentingcars.Service;

import com.example.rentingcars.ApiException.ApiException;
import com.example.rentingcars.Model.Date;
import com.example.rentingcars.Repository.DateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DateService {
    private final DateRepository dateRepository;
    public List<Date> getAllDAte(){
        return dateRepository.findAll();
    }
    public void  addDate(Date date){
        dateRepository.save(date);
    }
    public void updateDate(Integer id,Date date){
        Date oldDate =dateRepository.findDateById(id);
        if (oldDate == null) {
            throw new ApiException("The date not found");
        }
 oldDate.setStart_date(date.getStart_date());
        oldDate.setEnd_date(date.getEnd_date());
        dateRepository.save(date);
    }
    public void deleteDate(Integer id) {
        Date date = dateRepository.findDateById(id);

        if (date == null) {
            throw new ApiException("The date not found");
        }
        dateRepository.delete(date);
    }
}
