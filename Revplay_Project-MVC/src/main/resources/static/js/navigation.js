// --- Persistent Navigation (SPA-lite) ---
const NavigationController = (() => {
    function init() {
        document.body.addEventListener("click", handleLinkClick);
        window.addEventListener("popstate", handlePopState);
        interceptForms();
    }

    async function handleLinkClick(e) {
        const link = e.target.closest("a");
        if (!link) return;

        const href = link.getAttribute("href");
        if (!href || href.startsWith("#") || href.startsWith("javascript:") || link.target === "_blank") return;

        // Only AJAX internal user paths (avoid logout, external, etc.)
        if (href.includes("/user/") || href.includes("/search")) {
            e.preventDefault();
            navigateTo(href);
        }
    }

    async function navigateTo(url, push = true) {
        try {
            console.log("Navigating to:", url);
            const response = await fetch(url);
            if (!response.ok) throw new Error("Navigation failed");

            const html = await response.text();
            const parser = new DOMParser();
            const doc = parser.parseFromString(html, "text/html");

            // 1. Update Title
            document.title = doc.title;

            // 2. Replace Main Content
            const newContent = doc.querySelector(".main-content");
            const currentContent = document.querySelector(".main-content");
            if (newContent && currentContent) {
                currentContent.innerHTML = newContent.innerHTML;

                // Scroll to top
                currentContent.scrollTop = 0;
                const scrollArea = currentContent.querySelector(".content-scroll");
                if (scrollArea) scrollArea.scrollTop = 0;
            }

            // 3. Update Sidebar Active State
            updateSidebar(url);

            // 4. Update URL
            if (push) history.pushState({ url }, doc.title, url);

            // 5. Re-initialize Dynamic Content (Songs, etc.)
            if (window.reinitPlayerContent) {
                window.reinitPlayerContent();
            }

            // 6. Re-bind forms in new content
            interceptForms();

        } catch (err) {
            console.error("AJAX Navigation Error:", err);
            window.location.href = url; // Fallback to full reload
        }
    }

    function handlePopState(e) {
        if (e.state && e.state.url) {
            navigateTo(e.state.url, false);
        } else {
            // If no state, try current URL
            navigateTo(window.location.pathname + window.location.search, false);
        }
    }

    function updateSidebar(url) {
        const sidebarLinks = document.querySelectorAll(".sidebar .nav-link");
        sidebarLinks.forEach(link => {
            const href = link.getAttribute("href");
            if (url.includes(href)) {
                link.classList.add("active");
                link.classList.remove("inactive");
            } else {
                link.classList.remove("active");
                link.classList.add("inactive");
            }
        });
    }

    function interceptForms() {
        const forms = document.querySelectorAll("form.search-form");
        forms.forEach(form => {
            if (form.getAttribute("data-nav-intercept")) return;
            form.addEventListener("submit", async (e) => {
                e.preventDefault();
                const formData = new FormData(form);
                const params = new URLSearchParams(formData);
                const url = `${form.action}?${params.toString()}`;
                navigateTo(url);
            });
            form.setAttribute("data-nav-intercept", "true");
        });
    }

    return {
        init: init,
        navigate: navigateTo
    };
})();

document.addEventListener("DOMContentLoaded", () => {
    NavigationController.init();
});

// Expose for external use
window.ajaxNavigate = NavigationController.navigate;
