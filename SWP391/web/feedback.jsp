<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Feedback</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <style>
            body {
                background-color: #f7f7f7;
            }
            .feedback-container {
                max-width: 600px;
                margin: 50px auto;
                background: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            .feedback-header {
                font-size: 24px;
                margin-bottom: 20px;
                font-weight: bold;
            }
            .feedback-subheader {
                font-size: 18px;
                margin-bottom: 30px;
            }
            .star-rating {
                display: flex;
                justify-content: center;
                gap: 10px;
                margin-bottom: 30px;
            }
            .star {
                font-size: 30px;
                cursor: pointer;
                color: #ccc;
            }
            .star.selected {
                color: gold;
            }
            .feedback-options {
                display: flex;
                justify-content: center;
                gap: 20px;
                margin-bottom: 30px;
            }
            .feedback-option {
                cursor: pointer;
                border: 2px solid #eee;
                border-radius: 50%;
                padding: 10px;
                text-align: center;
                transition: border-color 0.3s;
            }
            .feedback-option.selected {
                border-color: gold;
            }
            .feedback-textarea {
                width: 100%;
                margin-bottom: 20px;
            }
            .feedback-submit {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="feedback-container">
            <form action="FeedbackServlet" method="post" id="feedbackForm">
                <div class="feedback-header">How is The Coffe Cute?</div>
                <div class="feedback-subheader" style="font-weight: normal;">We appreciate feedback about your experience.</div>

                <div class="star-rating">
                    <span class="star" data-value="1">&#9733;</span>
                    <span class="star" data-value="2">&#9733;</span>
                    <span class="star" data-value="3">&#9733;</span>
                    <span class="star" data-value="4">&#9733;</span>
                    <span class="star" data-value="5">&#9733;</span>
                </div>
                <input type="hidden" name="rating" id="rating" value="">

                <div class="feedback-subheader">What's driving your review? (Please choose)</div>
                <div class="feedback-options">
                    <div class="feedback-option" data-option="food">Food</div>
                    <div class="feedback-option" data-option="service">Service</div>
                    <div class="feedback-option" data-option="vibe">Vibe</div>
                    <div class="feedback-option" data-option="other">Other</div>
                </div>
                <input type="hidden" name="options" id="options" value="">

                <div class="feedback-subheader">Anything else? (optional)</div>
                <textarea class="form-control feedback-textarea" rows="3" name="comments" placeholder="Share your experience or offer suggestions to the staff..."></textarea>

                <button class="btn btn-primary feedback-submit" type="submit">Submit Feedback</button>
            </form>
        </div>

        <script>
            $(document).ready(function () {
                $('.star').on('click', function () {
                    var rating = $(this).data('value');
                    $('#rating').val(rating);
                    $('.star').each(function () {
                        if ($(this).data('value') <= rating) {
                            $(this).addClass('selected');
                        } else {
                            $(this).removeClass('selected');
                        }
                    });
                });

                $('.feedback-option').on('click', function () {
                    $(this).toggleClass('selected');
                    var selectedOptions = [];
                    $('.feedback-option.selected').each(function () {
                        selectedOptions.push($(this).data('option'));
                    });
                    $('#options').val(selectedOptions.join(','));
                });
            });
        </script>
    </body>
</html>
